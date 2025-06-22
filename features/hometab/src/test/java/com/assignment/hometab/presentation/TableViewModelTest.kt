package com.assignment.hometab.presentation

import app.cash.turbine.test
import com.assignment.core.bases.BaseViewState
import com.assignment.core.model.ResultWrapper
import com.assignment.hometab.common.TestConstants.ERROR_RESPONSE
import com.assignment.hometab.common.TestConstants.EXCEPTION
import com.assignment.hometab.common.TestConstants.PRODUCT
import com.assignment.hometab.common.TestConstants.SUCCESS_CATEGORY_LIST
import com.assignment.hometab.common.TestConstants.SUCCESS_ORDER_LIST
import com.assignment.hometab.common.TestConstants.SUCCESS_PRODUCT_LIST
import com.assignment.hometab.common.TestCoroutineRule
import com.assignment.hometab.tables.domain.model.Categories
import com.assignment.hometab.tables.domain.model.Order
import com.assignment.hometab.tables.domain.usecases.DeleteOrdersUseCase
import com.assignment.hometab.tables.domain.usecases.GetCategoriesUseCase
import com.assignment.hometab.tables.domain.usecases.GetOrdersUseCase
import com.assignment.hometab.tables.domain.usecases.GetProductsByCategoryIdUseCase
import com.assignment.hometab.tables.domain.usecases.InsertOrdersUseCase
import com.assignment.hometab.tables.presentation.TablesViewModel
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


@RunWith(MockitoJUnitRunner::class)
class TableViewModelTest {

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private lateinit var sut: TablesViewModel

    @Mock
    private lateinit var getCategoriesUseCase: GetCategoriesUseCase

    @Mock
    private lateinit var getProductsByCategoryIdUseCase: GetProductsByCategoryIdUseCase

    @Mock
    private lateinit var getOrdersUseCase: GetOrdersUseCase

    @Mock
    private lateinit var insertOrdersUseCase: InsertOrdersUseCase

    @Mock
    private lateinit var deleteOrdersUseCase: DeleteOrdersUseCase

    private lateinit var dataSource: TablesViewModelHelper


    @Before
    fun setUp() {
        sut = TablesViewModel(
            getCategoriesUseCase,
            getProductsByCategoryIdUseCase,
            getOrdersUseCase,
            insertOrdersUseCase,
            deleteOrdersUseCase
        )
        dataSource = TablesViewModelHelper(
            getCategoriesUseCase,
            getProductsByCategoryIdUseCase,
            getOrdersUseCase,
            insertOrdersUseCase,
            deleteOrdersUseCase
        )


    }

    // region getCategories UseCase test
    @Test
    fun `test getCategories when api success, categoryList should emit correct data`() =
        runTest {
            dataSource.prepareCategoriesSuccess()
            dataSource.prepareGetProductSuccess()

            sut.getCategoryList()
            getCategoriesUseCase().test {
                Assert.assertEquals(SUCCESS_CATEGORY_LIST, awaitItem())
                awaitComplete()
            }

            getProductsByCategoryIdUseCase()
            verify(getProductsByCategoryIdUseCase, Mockito.times(1)).invoke()
        }

    @Test
    fun `test getCategories when called, getCategories Should Not Interactions`() =
        runTest {
            dataSource.prepareCategoriesSuccess()
            sut.getCategoryList()
            verifyNoMoreInteractions(getCategoriesUseCase)
        }

    @Test
    fun `test getCategories when called, getCategories Should Called Once`() =
        runTest {
            getCategoriesUseCase()
            verify(getCategoriesUseCase, Mockito.times(1))()
        }

    @Test
    fun `test getCategories when api error, state should emit error data`() = runTest {
        dataSource.prepareCategoriesFailure()
        sut.getCategoryList()
        getCategoriesUseCase().test {
            Assert.assertEquals(ERROR_RESPONSE, awaitItem())
            awaitComplete()
        }
    }

    @Test
    fun `test getCategories when api get exception, state should emit exception data`() = runTest {
        dataSource.prepareCategoriesException()
        sut.getCategoryList()
        getCategoriesUseCase().test {
            Assert.assertEquals(EXCEPTION, awaitError().message)
        }
    }

    @Test
    fun `getCategoryList emits Loading then Success`() = runTest {
        dataSource.prepareCategoriesSuccess()
        dataSource.prepareGetProductSuccess()

        val job = launch {
            sut.state.test {
                Assert.assertEquals(BaseViewState.Idle, awaitItem())
                Assert.assertEquals(BaseViewState.Loading, awaitItem())
                awaitItem()
                cancelAndIgnoreRemainingEvents()
            }
        }

        sut.getCategoryList()
        job.join()
    }


    @Test
    fun `test getCategories when data is empty, getProductsByCategoryId not triggered`() = runTest {
        val emptyCategoryList = ResultWrapper.Success(emptyList<Categories>())
        Mockito.`when`(getCategoriesUseCase()).thenReturn(flowOf(emptyCategoryList))

        sut.getCategoryList()

        verify(getProductsByCategoryIdUseCase, Mockito.never()).invoke(Mockito.anyInt())
    }


    //endregion

    //region getProductsByCategoryId UseCase test
    @Test
    fun `test getProductsByCategoryId when api success, getProductsByCategoryId should emit correct data`() =
        runTest {
            dataSource.prepareGetProductSuccess()

            sut.getProductsByCategoryId(1)
            getProductsByCategoryIdUseCase(1).test {
                Assert.assertEquals(SUCCESS_PRODUCT_LIST, awaitItem())
                awaitComplete()
            }
        }

    @Test
    fun `test getProductsByCategoryId when called, getProductsByCategoryId Should Not Interactions`() =
        runTest {
            dataSource.prepareGetProductSuccess()
            sut.getProductsByCategoryId(1)
            verifyNoMoreInteractions(getCategoriesUseCase)
        }

    @Test
    fun `test getProductsByCategoryId when called, getProductsByCategoryId Should Called Once`() =
        runTest {
            getProductsByCategoryIdUseCase()
            verify(getProductsByCategoryIdUseCase, Mockito.times(1))()
        }

    @Test
    fun `test getProductsByCategoryId when api error, state should emit error data`() = runTest {
        dataSource.prepareGetProductFailure()
        sut.getProductsByCategoryId(1)
        getProductsByCategoryIdUseCase(1).test {
            Assert.assertEquals(ERROR_RESPONSE, awaitItem())
            awaitComplete()
        }
    }

    @Test
    fun `test getProductsByCategoryId when api get exception, state should emit exception data`() =
        runTest {
            dataSource.prepareGetProductException()
            sut.getProductsByCategoryId(1)
            getProductsByCategoryIdUseCase(1).test {
                Assert.assertEquals(EXCEPTION, awaitError().message)
            }
        }


    @Test
    fun `getProductsByCategoryId emits ShowOverLayLoading and DataLoaded`() = runTest {
        dataSource.prepareGetProductSuccess()

        val job = launch {
            sut.state.test {
                Assert.assertEquals(BaseViewState.Idle, awaitItem())
                Assert.assertEquals(BaseViewState.ShowOverLayLoading, awaitItem())
                Assert.assertEquals(BaseViewState.DataLoaded, awaitItem())
                cancelAndIgnoreRemainingEvents()
            }
        }

        sut.getProductsByCategoryId(1, showLoading = true)
        job.join()
    }


    //endregion


    // region getOrder UseCase test
    @Test
    fun `test getOrder when api success, getOrder should emit correct data`() =
        runTest {
            dataSource.prepareGetOrderSuccess()

            sut.getOrders()
            getOrdersUseCase().test {
                Assert.assertEquals(SUCCESS_ORDER_LIST, awaitItem())
                awaitComplete()
            }
        }

    @Test
    fun `test getOrder when called, getOrder Should Not Interactions`() =
        runTest {
            dataSource.prepareGetOrderSuccess()
            sut.getOrders()
            verifyNoMoreInteractions(getOrdersUseCase)
        }

    @Test
    fun `test getOrder when called, getOrder Should Called Once`() =
        runTest {
            getOrdersUseCase()
            verify(getOrdersUseCase, Mockito.times(1))()
        }

    @Test
    fun `test getOrder when api error, state should emit error data`() = runTest {
        dataSource.prepareGetOrderFailure()
        sut.getOrders()
        getOrdersUseCase().test {
            Assert.assertEquals(ERROR_RESPONSE, awaitItem())
            awaitComplete()
        }
    }

    @Test
    fun `test getOrder when api get exception, state should emit exception data`() = runTest {
        dataSource.prepareGetOrderException()
        sut.getOrders()
        getOrdersUseCase().test {
            Assert.assertEquals(EXCEPTION, awaitError().message)
        }
    }

    @Test
    fun `test getOrders with empty list, emits zero state`() = runTest {
        val emptyOrderList = ResultWrapper.Success(emptyList<Order>())
        Mockito.`when`(getOrdersUseCase()).thenReturn(flowOf(emptyOrderList))

        sut.getOrders()

        sut.orders.test {
            val result = awaitItem()
            Assert.assertEquals("0", result?.first)
            Assert.assertEquals(0.0, result?.second ?: 0.01, 0.01)
            cancelAndIgnoreRemainingEvents()
        }
    }

    //endregion


    // region insertOrder UseCase test
    @Test
    fun `test insertOrder when api success, insertOrder should emit correct data`() =
        runTest {
            dataSource.prepareInsertOrderSuccess()
            dataSource.prepareGetOrderSuccess()

            sut.insertOrder(PRODUCT)
            insertOrdersUseCase().test {
                Assert.assertEquals(ResultWrapper.Success(Unit), awaitItem())
                awaitComplete()
            }

            getOrdersUseCase()
            verify(getOrdersUseCase, Mockito.times(1)).invoke()
        }

    @Test
    fun `test insertOrder when called, insertOrder Should Not Interactions`() =
        runTest {
            dataSource.prepareInsertOrderSuccess()
            sut.insertOrder(PRODUCT)
            verifyNoMoreInteractions(insertOrdersUseCase)
        }

    @Test
    fun `test insertOrder when called, insertOrder Should Called Once`() =
        runTest {
            insertOrdersUseCase()
            verify(insertOrdersUseCase, Mockito.times(1))()
        }

    @Test
    fun `test insertOrder when api error, state should emit error data`() = runTest {
        dataSource.prepareInsertOrderFailure()
        sut.insertOrder(PRODUCT)
        insertOrdersUseCase().test {
            Assert.assertEquals(ERROR_RESPONSE, awaitItem())
            awaitComplete()
        }
    }

    @Test
    fun `test insertOrder when api get exception, state should emit exception data`() = runTest {
        dataSource.prepareInsertOrderException()
        sut.insertOrder(PRODUCT)
        insertOrdersUseCase().test {
            Assert.assertEquals(EXCEPTION, awaitError().message)
        }
    }


    @Test
    fun `insertOrder emits DataLoaded after success`() = runTest {
        dataSource.prepareInsertOrderSuccess()
        dataSource.prepareGetOrderSuccess()

        val job = launch {
            sut.state.test {
                Assert.assertEquals(BaseViewState.Idle, awaitItem())
                Assert.assertEquals(BaseViewState.DataLoaded, awaitItem())
                cancelAndIgnoreRemainingEvents()
            }
        }

        sut.insertOrder(PRODUCT)
        job.join()
    }

    //endregion

    // region deleteOrder UseCase test
    @Test
    fun `test deleteOrder when api success, deleteOrder should emit correct data`() =
        runTest {
            dataSource.prepareDeleteOrderSuccess()

            sut.deleteOrders()
            deleteOrdersUseCase().test {
                Assert.assertEquals(ResultWrapper.Success(Unit), awaitItem())
                awaitComplete()
            }
        }

    @Test
    fun `test deleteOrder when called, deleteOrder Should Not Interactions`() =
        runTest {
            dataSource.prepareDeleteOrderSuccess()
            sut.deleteOrders()
            verifyNoMoreInteractions(deleteOrdersUseCase)
        }

    @Test
    fun `test deleteOrder when called, deleteOrder Should Called Once`() =
        runTest {
            deleteOrdersUseCase()
            verify(deleteOrdersUseCase, Mockito.times(1))()
        }

    @Test
    fun `test deleteOrder when api error, state should emit error data`() = runTest {
        dataSource.prepareDeleteOrderFailure()
        sut.deleteOrders()
        deleteOrdersUseCase().test {
            Assert.assertEquals(ERROR_RESPONSE, awaitItem())
            awaitComplete()
        }
    }

    @Test
    fun `test deleteOrder when api get exception, state should emit exception data`() = runTest {
        dataSource.prepareDeleteOrderException()
        sut.deleteOrders()
        deleteOrdersUseCase().test {
            Assert.assertEquals(EXCEPTION, awaitError().message)
        }
    }
    //endregion



}

