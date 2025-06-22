package com.assignment.hometab.repository

import com.assignment.caching.manager.BaseManager
import com.assignment.caching.manager.CachingManager
import com.assignment.caching.manager.ProviderEnum
import com.assignment.core.model.ResultWrapper
import com.assignment.hometab.common.TestConstants.ORDER_ENTITY
import com.assignment.hometab.common.TestConstants.ORDER_LIST_ENTITY
import com.assignment.hometab.common.TestConstants.RESULT_EXCEPTION
import com.assignment.hometab.common.TestCoroutineRule
import com.assignment.hometab.tables.data.mapper.entityToOrderItem
import com.assignment.hometab.tables.data.mapper.entityToOrderList
import com.assignment.hometab.tables.data.repository.OrdersLocalRepository
import kotlinx.coroutines.flow.single
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class OrderLocalRepositoryTest {

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()


    private lateinit var sut: OrdersLocalRepository

    @Mock
    private lateinit var cachingManager: CachingManager

    @Mock
    private lateinit var mockBaseManager: BaseManager

    @Before
    fun setUp() {
        Mockito.`when`(cachingManager.getProvider(ProviderEnum.ROOM))
            .thenReturn(mockBaseManager)
        sut = OrdersLocalRepository(cachingManager)
    }

    @Test
    fun `test getOrders when cache success, getOrders should emit success data`() {
        testCoroutineRule.runTest {
            prepareGetOrderSuccess()
            val result = sut.getOrders()
            Assert.assertEquals(
                ResultWrapper.Success(
                    ORDER_LIST_ENTITY.entityToOrderList()
                ), result.single()
            )
        }
    }

    @Test
    fun `test getOrders when cache failure, getOrders should emit failure data`() {
        testCoroutineRule.runTest {
            prepareGetOrderError()
            val result = sut.getOrders()
            Assert.assertEquals(ResultWrapper.Error(RESULT_EXCEPTION), result.single())
        }
    }


    @Test
    fun `test insertOrder when cache success, insertOrder should emit success data`() {
        testCoroutineRule.runTest {
            prepareInsertOrderSuccess()
            val result = sut.insertOrder(ORDER_ENTITY.entityToOrderItem())
            Assert.assertEquals(
                ResultWrapper.Success(
                    Unit
                ), result.single()
            )
        }
    }

    @Test
    fun `test insertOrder when cache failure, insertOrder should emit failure data`() {
        testCoroutineRule.runTest {
            prepareInsertOrderError()
            val result = sut.insertOrder(ORDER_ENTITY.entityToOrderItem())
            Assert.assertEquals(ResultWrapper.Error(RESULT_EXCEPTION), result.single())
        }
    }

    @Test
    fun `test deleteOrderItem when cache success, deleteOrderItem should emit success data`() {
        testCoroutineRule.runTest {
            prepareDeleteOrderItemSuccess()
            val result = sut.deleteOrderItem(ORDER_ENTITY.entityToOrderItem())
            Assert.assertEquals(
                ResultWrapper.Success(
                    Unit
                ), result.single()
            )
        }
    }

    @Test
    fun `test deleteOrderItem when cache failure, deleteOrderItem should emit failure data`() {
        testCoroutineRule.runTest {
            prepareDeleteOrderItemError()
            val result = sut.deleteOrderItem(ORDER_ENTITY.entityToOrderItem())
            Assert.assertEquals(ResultWrapper.Error(RESULT_EXCEPTION), result.single())
        }
    }

    @Test
    fun `test deleteOrders when cache success, deleteOrders should emit success data`() {
        testCoroutineRule.runTest {
            prepareDeleteAllOrdersSuccess()
            val result = sut.deleteOrders()
            Assert.assertEquals(
                ResultWrapper.Success(
                    Unit
                ), result.single()
            )
        }
    }

    @Test
    fun `test deleteOrders when cache failure, deleteOrders should emit failure data`() {
        testCoroutineRule.runTest {
            prepareDeleteAllOrdersError()
            val result = sut.deleteOrders()
            Assert.assertEquals(ResultWrapper.Error(RESULT_EXCEPTION), result.single())
        }
    }

    private suspend fun prepareGetOrderSuccess() {
        Mockito.`when`(cachingManager.getProvider(ProviderEnum.ROOM).getOrders())
            .thenReturn(ResultWrapper.Success(ORDER_LIST_ENTITY))
    }

    private suspend fun prepareGetOrderError() {
        Mockito.`when`(cachingManager.getProvider(ProviderEnum.ROOM).getOrders())
            .thenReturn(ResultWrapper.Error(RESULT_EXCEPTION))
    }

    private suspend fun prepareInsertOrderSuccess() {
        Mockito.`when`(
            cachingManager.getProvider(ProviderEnum.ROOM).insertOrder(ORDER_ENTITY)
        )
            .thenReturn(ResultWrapper.Success(Unit))
    }

    private suspend fun prepareInsertOrderError() {
        Mockito.`when`(
            cachingManager.getProvider(ProviderEnum.ROOM).insertOrder(ORDER_ENTITY)
        )
            .thenReturn(ResultWrapper.Error(RESULT_EXCEPTION))
    }

    private suspend fun prepareDeleteOrderItemSuccess() {
        Mockito.`when`(
            cachingManager.getProvider(ProviderEnum.ROOM).deleteOrderItem(ORDER_ENTITY)
        )
            .thenReturn(ResultWrapper.Success(Unit))
    }

    private suspend fun prepareDeleteOrderItemError() {
        Mockito.`when`(
            cachingManager.getProvider(ProviderEnum.ROOM).deleteOrderItem(ORDER_ENTITY)
        )
            .thenReturn(ResultWrapper.Error(RESULT_EXCEPTION))
    }

    private suspend fun prepareDeleteAllOrdersSuccess() {
        Mockito.`when`(
            cachingManager.getProvider(ProviderEnum.ROOM).deleteAllData()
        )
            .thenReturn(ResultWrapper.Success(Unit))
    }

    private suspend fun prepareDeleteAllOrdersError() {
        Mockito.`when`(
            cachingManager.getProvider(ProviderEnum.ROOM).deleteAllData()
        )
            .thenReturn(ResultWrapper.Error(RESULT_EXCEPTION))
    }

}