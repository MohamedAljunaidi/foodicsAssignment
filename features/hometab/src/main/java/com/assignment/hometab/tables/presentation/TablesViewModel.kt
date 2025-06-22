package com.assignment.hometab.tables.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import com.assignment.core.bases.BaseViewModel
import com.assignment.core.bases.BaseViewState
import com.assignment.core.model.ResultException
import com.assignment.core.model.ResultWrapper
import com.assignment.hometab.tables.domain.model.Categories
import com.assignment.hometab.tables.domain.model.Order
import com.assignment.hometab.tables.domain.model.Products
import com.assignment.hometab.tables.domain.usecases.DeleteOrdersUseCase
import com.assignment.hometab.tables.domain.usecases.GetCategoriesUseCase
import com.assignment.hometab.tables.domain.usecases.GetOrdersUseCase
import com.assignment.hometab.tables.domain.usecases.GetProductsByCategoryIdUseCase
import com.assignment.hometab.tables.domain.usecases.InsertOrdersUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class TablesViewModel(
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val getProductsByCategoryIdUseCase: GetProductsByCategoryIdUseCase,
    private val getOrdersUseCase: GetOrdersUseCase,
    private val insertOrdersUseCase: InsertOrdersUseCase,
    private val deleteOrdersUseCase: DeleteOrdersUseCase,
) :
    BaseViewModel() {

    private var hasLoadedCategory = false

    private var _categories: MutableStateFlow<List<Categories>?> =
        MutableStateFlow(null)
    val categories: StateFlow<List<Categories>?> =
        _categories.asStateFlow()

    private var _products: MutableStateFlow<List<Products>?> =
        MutableStateFlow(null)
    val products: StateFlow<List<Products>?> =
        _products.asStateFlow()

    private var _orders: MutableStateFlow<Pair<String, Double>?> =
        MutableStateFlow(
            Pair(
                "0",
                0.0
            )
        )
    val orders: StateFlow<Pair<String, Double>?> =
        _orders.asStateFlow()


    var selectedTabIndex by mutableIntStateOf(0)
        private set

    fun onTabSelected(index: Int, categoryId: Int) {
        selectedTabIndex = index
        getProductsByCategoryId(categoryId, true)
    }

    fun getCategoryList() {
        if (!hasLoadedCategory){
            hasLoadedCategory = true
            launchCoroutine(coroutineExceptionHandler) {

                getCategoriesUseCase()
                    .onStart {
                        _state.emit(BaseViewState.Loading)
                    }.collectLatest {
                        when (it) {
                            is ResultWrapper.Success -> {
                                it.data?.let { categories ->
                                    _categories.emit(categories)
                                    val categoryId = categories.firstOrNull()?.id

                                    categoryId?.let { it1 -> getProductsByCategoryId(it1) }
                                }
                            }

                            is ResultWrapper.Error -> _state.emit(
                                BaseViewState.Error(
                                    ResultException(it.error.errorModel)
                                )
                            )

                        }
                    }
            }
        }
    }

    fun getProductsByCategoryId(categoryId: Int, showLoading: Boolean = false) {
        launchCoroutine(coroutineExceptionHandler) {

            getProductsByCategoryIdUseCase(categoryId).onStart {
                if (showLoading) {
                    _state.emit(BaseViewState.ShowOverLayLoading)
                }
            }
                .collectLatest {
                    when (it) {
                        is ResultWrapper.Success -> {
                            _products.emit(it.data)
                        }

                        is ResultWrapper.Error -> _state.emit(
                            BaseViewState.Error(
                                ResultException(it.error.errorModel)
                            )
                        )

                    }
                    _state.emit(BaseViewState.DataLoaded)
                }

        }
    }

    fun getOrders() {
        launchCoroutine(coroutineExceptionHandler) {

            getOrdersUseCase()
                .collectLatest {
                    when (it) {
                        is ResultWrapper.Success -> {
                            val totalPrice = it.data?.sumOf { it.price ?: 0.0 } ?: 0.0
                            _orders.emit(Pair(it.data?.size.toString(), totalPrice))


                        }

                        is ResultWrapper.Error -> _state.emit(
                            BaseViewState.Error(
                                ResultException(it.error.errorModel)
                            )
                        )

                    }
                }
        }
    }

    fun insertOrder(product: Products) {
        launchCoroutine(coroutineExceptionHandler) {

            val order = Order(
                name = product.name,
                image = product.image,
                price = product.price,
                description = product.description
            )
            insertOrdersUseCase(order)
                .collectLatest {
                    when (it) {
                        is ResultWrapper.Success -> {
                            getOrders()
                        }

                        is ResultWrapper.Error -> _state.emit(
                            BaseViewState.Error(
                                ResultException(it.error.errorModel)
                            )
                        )

                    }
                    _state.emit(BaseViewState.DataLoaded)
                }
        }
    }

    fun deleteOrders() {
        launchCoroutine(coroutineExceptionHandler) {
            deleteOrdersUseCase()
                .collectLatest {
                    when (it) {
                        is ResultWrapper.Success -> {
                            _orders.emit(Pair("0", 0.0))
                        }

                        is ResultWrapper.Error -> _state.emit(
                            BaseViewState.Error(
                                ResultException(it.error.errorModel)
                            )
                        )

                    }
                    _state.emit(BaseViewState.DataLoaded)
                }
        }
    }


}