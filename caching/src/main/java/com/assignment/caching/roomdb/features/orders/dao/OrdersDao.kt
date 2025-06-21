package com.assignment.caching.roomdb.features.orders.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.assignment.caching.roomdb.common.BaseDao
import com.assignment.caching.roomdb.features.orders.entities.OrderEntity

@Dao
interface OrdersDao : BaseDao<OrderEntity> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrder(obj: OrderEntity)

    @Query("SELECT * FROM order_table")
    fun getOrders(): List<OrderEntity>

    @Query("DELETE FROM order_table")
    fun deleteAllData()

    @Delete
    fun deleteOrderItem(orderEntity: OrderEntity)
}
