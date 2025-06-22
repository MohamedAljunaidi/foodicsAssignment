package com.assignment.hometab.presentation

import com.assignment.core.model.ResultWrapper
import com.assignment.hometab.common.TestConstants.ERROR_RESPONSE
import com.assignment.hometab.common.TestConstants.EXCEPTION
import com.assignment.hometab.common.TestConstants.SUCCESS_CATEGORY_LIST
import com.assignment.hometab.common.TestConstants.SUCCESS_ORDER_LIST
import com.assignment.hometab.common.TestConstants.SUCCESS_PRODUCT_LIST
import com.assignment.hometab.tables.domain.usecases.DeleteOrdersUseCase
import com.assignment.hometab.tables.domain.usecases.GetCategoriesUseCase
import com.assignment.hometab.tables.domain.usecases.GetOrdersUseCase
import com.assignment.hometab.tables.domain.usecases.GetProductsByCategoryIdUseCase
import com.assignment.hometab.tables.domain.usecases.InsertOrdersUseCase
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito


class TablesViewModelHelper(
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val getProductsByCategoryIdUseCase: GetProductsByCategoryIdUseCase,
    private val getOrdersUseCase: GetOrdersUseCase,
    private val insertOrdersUseCase: InsertOrdersUseCase,
    private val deleteOrdersUseCase: DeleteOrdersUseCase
) {


     suspend fun prepareCategoriesException() {
        Mockito.`when`(getCategoriesUseCase())
            .thenReturn(flow { throw IllegalArgumentException(EXCEPTION) })
    }

     suspend fun prepareCategoriesSuccess() {
        Mockito.`when`(getCategoriesUseCase())
            .thenReturn(flowOf(SUCCESS_CATEGORY_LIST))
    }

     suspend fun prepareCategoriesFailure() {
        Mockito.`when`(getCategoriesUseCase())
            .thenReturn(flowOf(ERROR_RESPONSE))
    }

     suspend fun prepareGetProductSuccess() {
        Mockito.`when`(getProductsByCategoryIdUseCase(1))
            .thenReturn(flowOf(SUCCESS_PRODUCT_LIST))
    }

     suspend fun prepareGetProductFailure() {
        Mockito.`when`(getProductsByCategoryIdUseCase(1))
            .thenReturn(flowOf(ERROR_RESPONSE))
    }

     suspend fun prepareGetProductException() {
        Mockito.`when`(getProductsByCategoryIdUseCase(1))
            .thenReturn(flow { throw IllegalArgumentException(EXCEPTION) })
    }

     suspend fun prepareGetOrderSuccess() {
        Mockito.`when`(getOrdersUseCase())
            .thenReturn(flowOf(SUCCESS_ORDER_LIST))
    }

     suspend fun prepareGetOrderFailure() {
        Mockito.`when`(getOrdersUseCase())
            .thenReturn(flowOf(ERROR_RESPONSE))
    }

     suspend fun prepareGetOrderException() {
        Mockito.`when`(getOrdersUseCase())
            .thenReturn(flow { throw IllegalArgumentException(EXCEPTION) })
    }

     suspend fun prepareInsertOrderSuccess() {
         Mockito.`when`(insertOrdersUseCase(any()))
             .thenReturn(flowOf(ResultWrapper.Success(Unit)))
    }

     suspend fun prepareInsertOrderFailure() {
        Mockito.`when`(insertOrdersUseCase(any()))
            .thenReturn(flowOf(ERROR_RESPONSE))
    }

     suspend fun prepareInsertOrderException() {
        Mockito.`when`(insertOrdersUseCase())
            .thenReturn(flow { throw IllegalArgumentException(EXCEPTION) })
    }

     suspend fun prepareDeleteOrderSuccess() {
        Mockito.`when`(deleteOrdersUseCase())
            .thenReturn(flowOf(ResultWrapper.Success(Unit)))
    }

     suspend fun prepareDeleteOrderFailure() {
        Mockito.`when`(deleteOrdersUseCase())
            .thenReturn(flowOf(ERROR_RESPONSE))
    }

     suspend fun prepareDeleteOrderException() {
        Mockito.`when`(deleteOrdersUseCase())
            .thenReturn(flow { throw IllegalArgumentException(EXCEPTION) })
    }
}
