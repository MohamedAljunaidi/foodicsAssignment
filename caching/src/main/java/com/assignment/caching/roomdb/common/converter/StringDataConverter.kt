package com.assignment.caching.roomdb.common.converter

import com.assignment.caching.extensions.type

/**
 * StringDataConverter is a base class for converting a list of String in a room database entity
 */
class StringDataConverter :
    ListConverter<String>(type<List<String>>())