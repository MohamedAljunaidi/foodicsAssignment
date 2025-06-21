package com.assignment.caching.roomdb.features.products.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.assignment.caching.roomdb.common.BaseDao
import com.assignment.caching.roomdb.features.products.entities.ProductsEntity

@Dao
interface ProductsDao : BaseDao<ProductsEntity> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProducts(obj: List<ProductsEntity>)

    @Transaction
    @Query("SELECT * FROM products_table WHERE categoryId = :categoryId")
    suspend fun getProductsByCategoryId(categoryId: Int): List<ProductsEntity>


    @Query("SELECT * FROM products_table")
    fun getAllProducts(): List<ProductsEntity>


    @Query("DELETE FROM products_table")
    fun deleteAllData()

}
