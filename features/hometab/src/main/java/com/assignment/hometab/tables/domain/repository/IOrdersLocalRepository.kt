package com.assignment.hometab.tables.domain.repository

import com.assignment.core.model.ResultWrapper
import com.assignment.hometab.tables.domain.model.Order
import kotlinx.coroutines.flow.Flow

interface IOrdersLocalRepository {
    fun getOrders(): Flow<ResultWrapper<List<Order>?>>
    fun insertOrder(order: Order?): Flow<ResultWrapper<Unit?>>
    fun deleteOrders(): Flow<ResultWrapper<Unit?>>
    fun deleteOrderItem(order: Order?): Flow<ResultWrapper<Unit?>>
}