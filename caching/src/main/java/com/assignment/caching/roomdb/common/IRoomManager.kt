package com.assignment.caching.roomdb.common

import com.assignment.caching.roomdb.features.categories.entities.CategoryEntity
import com.assignment.caching.roomdb.features.orders.entities.OrderEntity
import com.assignment.caching.roomdb.features.products.entities.ProductsEntity
import com.assignment.core.model.ResultWrapper


interface IRoomManager {

    suspend fun insertCategories(categoryEntity: List<CategoryEntity>): ResultWrapper<Unit>? = null

    suspend fun getCategories(): ResultWrapper<List<CategoryEntity>?>? = null

    suspend fun insertProducts(productsEntity: List<ProductsEntity>): ResultWrapper<Unit>? = null

    suspend fun getProductsByCategoryId(categoryId: Int): ResultWrapper<List<ProductsEntity>?>? = null
    suspend fun getAllProducts(): ResultWrapper<List<ProductsEntity>?>? = null


    suspend fun insertOrder(orderEntity: OrderEntity) : ResultWrapper<Unit>? = null

    suspend fun getOrders(): ResultWrapper<List<OrderEntity>?>? = null
    suspend fun deleteAllData(): ResultWrapper<Unit?>? = null

   suspend fun deleteOrderItem(orderEntity: OrderEntity): ResultWrapper<Unit?>? = null


}