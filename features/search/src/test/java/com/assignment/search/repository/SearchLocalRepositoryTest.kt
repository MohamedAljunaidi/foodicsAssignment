package com.assignment.search.repository

import com.assignment.caching.manager.BaseManager
import com.assignment.caching.manager.CachingManager
import com.assignment.caching.manager.ProviderEnum
import com.assignment.core.model.ResultWrapper
import com.assignment.search.common.TestConstants.PRODUCT_LIST_ENTITY
import com.assignment.search.common.TestConstants.RESULT_EXCEPTION
import com.assignment.search.common.TestCoroutineRule
import com.assignment.search.data.mapper.entityToProductList
import com.assignment.search.data.repository.SearchLocalRepository
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
class SearchLocalRepositoryTest {

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()


    private lateinit var sut: SearchLocalRepository

    @Mock
    private lateinit var cachingManager: CachingManager

    @Mock
    private lateinit var mockBaseManager: BaseManager

    @Before
    fun setUp() {
        Mockito.`when`(cachingManager.getProvider(ProviderEnum.ROOM))
            .thenReturn(mockBaseManager)
        sut = SearchLocalRepository(cachingManager)
    }

    @Test
    fun `test getProducts when cache success, getProducts should emit success data`() {
        testCoroutineRule.runTest {
            prepareSuccess()
            val result = sut.getProducts()
            Assert.assertEquals(
                ResultWrapper.Success(
                    PRODUCT_LIST_ENTITY.entityToProductList()
                ), result.single()
            )
        }
    }

    @Test
    fun `test getCategoryList when cache failure, getCategoryList should emit failure data`() {
        testCoroutineRule.runTest {
            prepareError()
            val result = sut.getProducts()
            Assert.assertEquals(ResultWrapper.Error(RESULT_EXCEPTION), result.single())
        }
    }

    private suspend fun prepareSuccess() {
        Mockito.`when`(cachingManager.getProvider(ProviderEnum.ROOM).getAllProducts())
            .thenReturn(ResultWrapper.Success(PRODUCT_LIST_ENTITY))
    }

    private suspend fun prepareError() {
        Mockito.`when`(cachingManager.getProvider(ProviderEnum.ROOM).getAllProducts())
            .thenReturn(ResultWrapper.Error(RESULT_EXCEPTION))
    }


}