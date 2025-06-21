package com.assignment.caching.roomdb.common

import com.assignment.caching.extensions.safeLocalDataCall
import com.assignment.caching.manager.BaseManager
import com.assignment.caching.roomdb.features.categories.entities.CategoryEntity
import com.assignment.caching.roomdb.features.orders.entities.OrderEntity
import com.assignment.caching.roomdb.features.products.entities.ProductsEntity
import com.assignment.core.model.ResultWrapper

class RoomManager(private val databaseRoom: DatabaseRoom) : BaseManager {

    override suspend fun insertCategories(categoryEntity: List<CategoryEntity>): ResultWrapper<Unit> {
       return safeLocalDataCall {
            databaseRoom.categoriesDao().insertCategories(categoryEntity)
        }
    }


    override suspend fun getCategories() =
        safeLocalDataCall {
            databaseRoom.categoriesDao().getCategories()
        }

    override suspend fun insertProducts(productsEntity: List<ProductsEntity>): ResultWrapper<Unit> =
        safeLocalDataCall { databaseRoom.productsDao().insertProducts(productsEntity) }


    override suspend fun getProducts(categoryId: Int) =
        safeLocalDataCall {
            databaseRoom.productsDao().getProducts(categoryId)
        }

    override suspend fun insertOrder(order: OrderEntity): ResultWrapper<Unit> {
        return safeLocalDataCall {
            databaseRoom.ordersDao().insertOrder(order)
        }
    }

    override suspend fun getOrders() =
        safeLocalDataCall {
            databaseRoom.ordersDao().getOrders()
        }

    override suspend fun deleteAllData() =
        safeLocalDataCall {
            databaseRoom.ordersDao().deleteAllData()
        }

    override suspend fun deleteOrderItem(order: OrderEntity) =
        safeLocalDataCall {
            databaseRoom.ordersDao().deleteOrderItem(order)
        }

}