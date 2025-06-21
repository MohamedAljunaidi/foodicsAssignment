package com.assignment.caching.roomdb.common

import androidx.room.Database
import androidx.room.RoomDatabase
import com.assignment.caching.roomdb.features.categories.dao.CategoriesDao
import com.assignment.caching.roomdb.features.categories.entities.CategoryEntity
import com.assignment.caching.roomdb.features.orders.dao.OrdersDao
import com.assignment.caching.roomdb.features.orders.entities.OrderEntity
import com.assignment.caching.roomdb.features.products.dao.ProductsDao
import com.assignment.caching.roomdb.features.products.entities.ProductsEntity

@Database(
    entities = [
        ProductsEntity::class,
        CategoryEntity::class,
        OrderEntity::class,
    ], version = RoomConstants.DATABASE_VERSION
)
abstract class DatabaseRoom : RoomDatabase() {
    abstract fun categoriesDao(): CategoriesDao
    abstract fun productsDao(): ProductsDao
    abstract fun ordersDao(): OrdersDao

}
