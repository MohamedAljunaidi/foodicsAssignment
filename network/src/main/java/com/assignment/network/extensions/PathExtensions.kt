package com.assignment.network.extensions

fun substitutePathParams(url: String, pathParams: Map<String, String>?): String {
    var substitutedUrl = url
    pathParams?.forEach { (key, value) ->
        substitutedUrl = substitutedUrl.replace("{$key}", value)
    }
    return substitutedUrl
}

