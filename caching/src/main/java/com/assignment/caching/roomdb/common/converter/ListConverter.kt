package com.assignment.caching.roomdb.common.converter

import androidx.room.TypeConverter
import com.assignment.caching.extensions.fromJson
import com.assignment.caching.extensions.toJson
import java.lang.reflect.Type

/**
 * ListConverter is a base class for converting a list of objects in a room database entity
 */
open class ListConverter<T>(private val type: Type) {

    @TypeConverter
    open fun mapStringToList(value: String?): List<T>? {
        return fromJson(value, type)
    }

    @TypeConverter
    open fun mapListToString(value: List<T>?): String? {
        return toJson(value, type)
    }
}


