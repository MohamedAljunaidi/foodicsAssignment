package com.assignment.network.extensions

import com.assignment.core.model.ServerExceptionType
import com.assignment.network.exceptions.NetworkException
import com.assignment.network.model.ApiErrors
import com.assignment.network.network.common.NetworkConstants
import com.assignment.network.result.NetworkResult
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.runBlocking
import java.io.IOException

inline fun safeApiCall(
    apiCall: () -> HttpResponse
): NetworkResult<HttpResponse> {
    return runCatching {
        val response = apiCall()
        if (!response.status.isSuccess()) {
            throw Exception()
        }
        response
    }.toNetworkResult()
}

inline fun <T, R> NetworkResult<T>.map(
    mapper: (T?) -> R?
): NetworkResult<R> {
    return when (this) {
        is NetworkResult.Success -> {
            runCatching {
                mapper(data)
            }.toNetworkResult()
        }
        is NetworkResult.Error -> {
            NetworkResult.Error(error)
        }
    }
}


fun <T> Result<T?>.toNetworkResult(): NetworkResult<T> = fold(
    onSuccess = { result -> NetworkResult.Success(result) },
    onFailure = { error -> NetworkResult.Error(error.parseErrorResponse()) }
)

fun Throwable.parseErrorResponse() = mapApiError(this)

private fun mapApiError(error: Throwable): NetworkException =
    when (error) {
        is IOException -> NetworkException.NoInternetFoundException()
        is ClientRequestException -> {
            val status = error.response.status.value
            val serverExceptionType = when {
                status == NetworkConstants.ErrorCodes.CLIENT_ERROR_UNAUTHORIZED -> ServerExceptionType.Unauthorized
                status == NetworkConstants.ErrorCodes.CLIENT_ERROR_FORBIDDEN -> ServerExceptionType.Forbidden
                status in NetworkConstants.ErrorCodes.SERVER_ERROR_RANGE_START..NetworkConstants.ErrorCodes.SERVER_ERROR_RANGE_END -> ServerExceptionType.ServerError
                else -> ServerExceptionType.ClientError
            }
            val body = runBlocking { error.response.bodyAsText() }
            val apiErrors = body.getModel<ApiErrors>()
            NetworkException.ApiErrorException(serverExceptionType, apiErrors)
        }
        else -> NetworkException.UnknownException(cause = error)
    }


