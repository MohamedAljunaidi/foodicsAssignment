package com.assignment.caching.extensions

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

/**
 * fromJson function used for converter object in room database
 * it casting the entity to Gson for room Database
 */
fun <T> fromJson(json: String?, type: Type): T? {
    return Gson().fromJson(json, type)
}

/**
 * toJson function used for converter object in room database
 * it casting the Gson to toJson for room Database
 */
fun <T> toJson(obj: T, type: Type): String? {
    return Gson().toJson(obj, type)
}

inline fun <reified T> type(): Type = object : TypeToken<T>() {}.type
