package com.assignment.caching.roomdb.features.products.converter

import com.assignment.caching.extensions.type
import com.assignment.caching.roomdb.common.converter.ObjectConverter
import com.assignment.caching.roomdb.features.categories.entities.CategoryEntity

class CategoryConverter :
    ObjectConverter<CategoryEntity>(type<CategoryEntity>())
