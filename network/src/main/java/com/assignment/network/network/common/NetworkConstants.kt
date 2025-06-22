package com.assignment.network.network.common

import com.assignment.network.BuildConfig

object NetworkConstants {

    const val TIMEOUT_IN_SECONDS = 30L
    const val BASE_URL = BuildConfig.BASE_URL
    object ErrorCodes {

        const val CLIENT_ERROR_UNAUTHORIZED = 401
        const val CLIENT_ERROR_FORBIDDEN = 403
        const val SERVER_ERROR_RANGE_START = 500
        const val SERVER_ERROR_RANGE_END = 599

    }

}