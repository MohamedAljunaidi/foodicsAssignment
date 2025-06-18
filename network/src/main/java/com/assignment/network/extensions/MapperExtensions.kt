package com.assignment.network.extensions

import com.assignment.core.model.ErrorModel
import com.assignment.core.model.ServerExceptionType
import com.assignment.network.model.ApiErrors
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

inline fun <reified T> String.getModel(): T? {
    return Gson().fromJson(this, type<T>())
}

inline fun <reified T> type(): Type = object : TypeToken<T>() {}.type

fun ApiErrors?.toErrorModel(serverExceptionType: ServerExceptionType) =
    ErrorModel(
        errorType = serverExceptionType,
        errorDescription = this?.message
    )

fun Throwable?.toErrorModel(serverExceptionType: ServerExceptionType) =
    ErrorModel(cause = this, errorType = serverExceptionType)
