package com.assignment.hometab.tables.data.repository

import com.assignment.caching.extensions.tryMapperQuery
import com.assignment.caching.manager.CachingManager
import com.assignment.caching.manager.ProviderEnum
import com.assignment.core.model.ResultWrapper
import com.assignment.hometab.tables.data.mapper.entityToOrderList
import com.assignment.hometab.tables.data.mapper.toOrderEntity
import com.assignment.hometab.tables.domain.model.Order
import com.assignment.hometab.tables.domain.repository.IOrdersLocalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class OrdersLocalRepository(private val cachingManager: CachingManager) :
    IOrdersLocalRepository {

    override fun getOrders(): Flow<ResultWrapper<List<Order>?>> = flow {
        val result = tryMapperQuery({
            cachingManager.getProvider(ProviderEnum.ROOM).getOrders()
        })
        { data ->
            data?.entityToOrderList()
        }
        emit(result)
    }


    override fun insertOrder(order: Order?): Flow<ResultWrapper<Unit?>> =
        flow {
            val result = tryMapperQuery({
                order?.let {
                    cachingManager.getProvider(ProviderEnum.ROOM)
                        .insertOrder(it.toOrderEntity())
                }
            }) {}
            emit(result)
        }


    override fun deleteOrders(): Flow<ResultWrapper<Unit?>> = flow {
        val result = tryMapperQuery({
            cachingManager.getProvider(ProviderEnum.ROOM).deleteAllData()
        })
        {}
        emit(result)
    }

    override fun deleteOrderItem(order: Order?): Flow<ResultWrapper<Unit?>> = flow {
        val result = tryMapperQuery({
            order?.let {
                cachingManager.getProvider(ProviderEnum.ROOM).deleteOrderItem(it.toOrderEntity())
            }
        })
        {}
        emit(result)
    }


}
