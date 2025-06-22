package com.assignment.hometab.repository

import com.assignment.core.model.ResultWrapper
import com.assignment.hometab.common.TestConstants.API_ERROR
import com.assignment.hometab.common.TestConstants.PRODUCT_LIST_RESPONSE
import com.assignment.hometab.common.TestCoroutineRule
import com.assignment.hometab.tables.data.HomeService
import com.assignment.hometab.tables.data.mapper.toProducts
import com.assignment.hometab.tables.data.repository.ProductsRemoteRepository
import com.assignment.network.result.NetworkResult
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
class ProductsRemoteRepositoryTest {

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()


    private lateinit var sut: ProductsRemoteRepository

    @Mock
    private lateinit var homeService: HomeService

    @Before
    fun setUp() {
        sut = ProductsRemoteRepository(homeService)
    }

    @Test
    fun `test getProducts when api success, getProducts should emit success data`() {
        testCoroutineRule.runTest {
            prepareSuccess()
            val result = sut.getProduct()
            Assert.assertEquals(
                ResultWrapper.Success(
                    PRODUCT_LIST_RESPONSE.toProducts()
                ), result.single()
            )
        }
    }

    @Test
    fun `test getProducts when api failure, getProducts should emit failure data`() {
        testCoroutineRule.runTest {
            prepareError()
            val result = sut.getProduct()
            Assert.assertEquals(ResultWrapper.Error(API_ERROR), result.single())
        }
    }

    private suspend fun prepareSuccess() {
        Mockito.`when`(homeService.getProducts())
            .thenReturn(NetworkResult.Success(PRODUCT_LIST_RESPONSE))
    }

    private suspend fun prepareError() {
        Mockito.`when`(homeService.getProducts())
            .thenReturn(NetworkResult.Error(API_ERROR))
    }
}