package com.assignment.caching.roomdb.common

import com.assignment.caching.roomdb.features.categories.entities.CategoryEntity
import com.assignment.caching.roomdb.features.products.entities.ProductsEntity
import com.assignment.core.model.ResultWrapper


interface IRoomManager {

    suspend fun insertCategories(categoryEntity: List<CategoryEntity>): ResultWrapper<Unit>? = null

    suspend fun getCategories(): ResultWrapper<List<CategoryEntity>?>? = null

    suspend fun insertProducts(productsEntity: List<ProductsEntity>): ResultWrapper<Unit>? = null

    suspend fun getProducts(categoryId: Int): ResultWrapper<List<ProductsEntity>?>? = null


}