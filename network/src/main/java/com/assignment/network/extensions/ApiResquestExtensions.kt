package com.assignment.network.extensions

import com.assignment.core.model.ResultWrapper
import com.assignment.network.result.NetworkResult

inline fun <DATA, DOMAIN> tryRequest(
    request: () -> NetworkResult<DATA>,
    dataToDomain: (DATA?) -> DOMAIN
) = try {
    when (val response = request()) {
        is NetworkResult.Success -> {
            ResultWrapper.Success(dataToDomain(response.data))
        }
        is NetworkResult.Error -> {
            ResultWrapper.Error(response.error)
        }
    }
} catch (e: Exception) {
    ResultWrapper.Error(e.parseErrorResponse())
}