package com.assignment.network.result

import com.assignment.network.exceptions.NetworkException


sealed class NetworkResult<out T> {

    data class Success<T>(val data: T?) : NetworkResult<T>()

    data class Error(val error: NetworkException) : NetworkResult<Nothing>()

}