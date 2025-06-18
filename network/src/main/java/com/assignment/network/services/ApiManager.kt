package com.assignment.network.services

import com.assignment.network.BuildConfig
import com.assignment.network.extensions.getModel
import com.assignment.network.extensions.map
import com.assignment.network.extensions.safeApiCall
import com.assignment.network.extensions.substitutePathParams
import com.assignment.network.network.common.NetworkConstants
import com.assignment.network.result.NetworkResult
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import org.koin.core.annotation.InjectedParam


class ApiManager(
    @InjectedParam val client: HttpClient
) {
    suspend inline fun <reified T> getRequest(
        url: String,
        pathParams: Map<String, String>? = mapOf(),
        headersMap: Map<String, String>? = mapOf(),
        queryParamsMap: Map<String, Any?>? = mapOf()
    ): NetworkResult<T> =
        safeApiCall {
            client.get {
                url(NetworkConstants.BASE_URL + substitutePathParams(url, pathParams) + "?key=${BuildConfig.API_KEY}")
                headersMap?.forEach { (key, value) -> header(key, value) }
                queryParamsMap?.forEach { (key, value) -> parameter(key, value) }
            }
        }.map { response ->
            response?.bodyAsText()?.getModel()
        }
}
