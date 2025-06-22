package com.assignment.hometab.repository

import com.assignment.caching.manager.BaseManager
import com.assignment.caching.manager.CachingManager
import com.assignment.caching.manager.ProviderEnum
import com.assignment.core.model.ResultWrapper
import com.assignment.hometab.common.TestConstants.PRODUCT_LIST_ENTITY
import com.assignment.hometab.common.TestConstants.RESULT_EXCEPTION
import com.assignment.hometab.common.TestCoroutineRule
import com.assignment.hometab.tables.data.mapper.entityToProductList
import com.assignment.hometab.tables.data.repository.ProductsLocalRepository
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
class ProductsLocalRepositoryTest {

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()


    private lateinit var sut: ProductsLocalRepository

    @Mock
    private lateinit var cachingManager: CachingManager

    @Mock
    private lateinit var mockBaseManager: BaseManager

    @Before
    fun setUp() {
        Mockito.`when`(cachingManager.getProvider(ProviderEnum.ROOM))
            .thenReturn(mockBaseManager)
        sut = ProductsLocalRepository(cachingManager)
    }

    @Test
    fun `test getProductsByCategoryId when cache success, getProductsByCategoryId should emit success data`() {
        testCoroutineRule.runTest {
            prepareSuccess()
            val result = sut.getProductsByCategoryId(1)
            Assert.assertEquals(
                ResultWrapper.Success(
                    PRODUCT_LIST_ENTITY.entityToProductList()
                ), result.single()
            )
        }
    }

    @Test
    fun `test getProductsByCategoryId when cache failure, getProductsByCategoryId should emit failure data`() {
        testCoroutineRule.runTest {
            prepareError()
            val result = sut.getProductsByCategoryId(1)
            Assert.assertEquals(ResultWrapper.Error(RESULT_EXCEPTION), result.single())
        }
    }

    @Test
    fun `test insertProducts when cache success, insertProducts should emit success data`() {
        testCoroutineRule.runTest {
            prepareInsertCategoriesSuccess()
            val result = sut.insertProducts(PRODUCT_LIST_ENTITY.entityToProductList())
            Assert.assertEquals(
                ResultWrapper.Success(
                    Unit
                ), result.single()
            )
        }
    }

    @Test
    fun `test insertProducts when cache failure, insertProducts should emit failure data`() {
        testCoroutineRule.runTest {
            prepareInsertCategoriesError()
            val result = sut.insertProducts(PRODUCT_LIST_ENTITY.entityToProductList())
            Assert.assertEquals(ResultWrapper.Error(RESULT_EXCEPTION), result.single())
        }
    }

    private suspend fun prepareSuccess() {
        Mockito.`when`(cachingManager.getProvider(ProviderEnum.ROOM).getProductsByCategoryId(1))
            .thenReturn(ResultWrapper.Success(PRODUCT_LIST_ENTITY))
    }

    private suspend fun prepareError() {
        Mockito.`when`(cachingManager.getProvider(ProviderEnum.ROOM).getProductsByCategoryId(1))
            .thenReturn(ResultWrapper.Error(RESULT_EXCEPTION))
    }

    private suspend fun prepareInsertCategoriesSuccess() {
        Mockito.`when`(cachingManager.getProvider(ProviderEnum.ROOM).insertProducts(PRODUCT_LIST_ENTITY))
            .thenReturn(ResultWrapper.Success(Unit))
    }

    private suspend fun prepareInsertCategoriesError() {
        Mockito.`when`(cachingManager.getProvider(ProviderEnum.ROOM).insertProducts(PRODUCT_LIST_ENTITY))
            .thenReturn(ResultWrapper.Error(RESULT_EXCEPTION))
    }

}