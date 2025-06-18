package com.assignment.network.extensions



fun substitutePathParams(url: String, pathParams: Map<String, String>?): String {
    var substitutedUrl = url
    pathParams?.forEach { (key, value) ->
        substitutedUrl = substitutedUrl.replace("{$key}", value)
    }
    return substitutedUrl
}

private operator fun <K, V> Map<out K, V>.plus(map: Map<out K, V>?): Map<K, V> =
    LinkedHashMap(this).apply {
        if (map != null) putAll(map)
    }

private fun String.toPathParamRepresentation() = "{$this}"