package com.assignment.search.presentation

import app.cash.turbine.test
import com.assignment.core.bases.BaseViewState
import com.assignment.core.model.ResultWrapper
import com.assignment.search.common.TestConstants.ERROR_RESPONSE
import com.assignment.search.common.TestConstants.SUCCESS_PRODUCT_LIST
import com.assignment.search.common.TestConstants.EXCEPTION
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import kotlinx.coroutines.test.runTest
import org.mockito.Mockito
import org.mockito.Mockito.verifyNoMoreInteractions
import org.mockito.Mockito.verify
import com.assignment.search.common.TestCoroutineRule
import com.assignment.search.domain.model.Categories
import com.assignment.search.domain.model.Products
import com.assignment.search.domain.usecases.SearchProductsUseCase
import com.assignment.search.presentations.SearchViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.advanceUntilIdle


@RunWith(MockitoJUnitRunner::class)
class SearchViewModelTest {

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private lateinit var sut: SearchViewModel

    @Mock
    private lateinit var searchProductsUseCase: SearchProductsUseCase


    @Before
    fun setUp() {
        sut = SearchViewModel(
            searchProductsUseCase)

    }

    // region getAllProduct UseCase test
    @Test
    fun `test getAllProduct when api success, getAllProduct should emit correct data`() =
        runTest {
            prepareGetAllProductsSuccess()

            sut.getAllProducts()
            searchProductsUseCase().test {
                Assert.assertEquals(SUCCESS_PRODUCT_LIST, awaitItem())
                awaitComplete()
            }
        }

    @Test
    fun `test getAllProduct when called, getAllProduct Should Not Interactions`() =
        runTest {
            prepareGetAllProductsSuccess()
            sut.getAllProducts()
            verifyNoMoreInteractions(searchProductsUseCase)
        }

    @Test
    fun `test getAllProduct when called, getAllProduct Should Called Once`() =
        runTest {
            searchProductsUseCase()
            verify(searchProductsUseCase, Mockito.times(1))()
        }

    @Test
    fun `test getAllProduct when api error, state should emit error data`() = runTest {
        prepareGetAllProductsFailure()
        sut.getAllProducts()
        searchProductsUseCase().test {
            Assert.assertEquals(ERROR_RESPONSE, awaitItem())
            awaitComplete()
        }
    }

    @Test
    fun `test getAllProduct when api get exception, state should emit exception data`() = runTest {
        prepareGetAllProductException()
        sut.getAllProducts()
        searchProductsUseCase().test {
            Assert.assertEquals(EXCEPTION, awaitError().message)
        }
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test setSearchQuery filters products by query`() = runTest {
        val testProducts = listOf(
            Products(id = 1, name = "Pizza", price = 10.0, description ="", image ="",  category = Categories(id = 1, name = "Pizza")),
            Products(id = 2, name = "Burger", price = 10.0, description ="", image ="",  category = Categories(id = 1, name = "Pizza")),
            Products(id = 3, name = "Pasta", price = 10.0, description ="", image ="",  category = Categories(id = 1, name = "Pizza")),
        )


        Mockito.`when`(searchProductsUseCase()).thenReturn(flowOf(ResultWrapper.Success(testProducts)))
        sut.getAllProducts()
        advanceUntilIdle()

        sut.setSearchQuery("Piz")

        Assert.assertEquals(1, sut.filteredProducts.value.size)
        Assert.assertEquals("Pizza", sut.filteredProducts.value.first().name)
    }

    @Test
    fun `test setSearchQuery with empty query resets filtered list`() = runTest {
        sut.setSearchQuery("")
        Assert.assertTrue(sut.filteredProducts.value.isEmpty())
    }

    @Test
    fun `test getAllProducts emits Loading then DataLoaded`() = runTest {
        prepareGetAllProductsSuccess()

        val job = launch {
            sut.state.test {
                Assert.assertEquals(BaseViewState.Idle, awaitItem())
                Assert.assertEquals(BaseViewState.Loading, awaitItem())
                Assert.assertEquals(BaseViewState.DataLoaded, awaitItem())
                cancelAndIgnoreRemainingEvents()
            }
        }

        sut.getAllProducts()
        job.join()
    }

    //endregion


    suspend fun prepareGetAllProductsSuccess() {
        Mockito.`when`(searchProductsUseCase())
            .thenReturn(flowOf(SUCCESS_PRODUCT_LIST))
    }

    suspend fun prepareGetAllProductsFailure() {
        Mockito.`when`(searchProductsUseCase())
            .thenReturn(flowOf(ERROR_RESPONSE))
    }

    suspend fun prepareGetAllProductException() {
        Mockito.`when`(searchProductsUseCase())
            .thenReturn(flow { throw IllegalArgumentException(EXCEPTION) })
    }



}

