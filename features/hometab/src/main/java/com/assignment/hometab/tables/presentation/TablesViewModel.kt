package com.assignment.hometab.tables.presentation

import com.assignment.core.bases.BaseViewModel
import com.assignment.core.bases.BaseViewState
import com.assignment.core.model.ResultException
import com.assignment.core.model.ResultWrapper
import com.assignment.hometab.tables.domain.model.Categories
import com.assignment.hometab.tables.domain.model.Products
import com.assignment.hometab.tables.domain.usecases.GetCategoriesUseCase
import com.assignment.hometab.tables.domain.usecases.GetProductsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class TablesViewModel(
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val getProductsUseCase: GetProductsUseCase
) :
    BaseViewModel() {

    private var _categories: MutableStateFlow<List<Categories>?> =
        MutableStateFlow(null)
    val categories: StateFlow<List<Categories>?> =
        _categories.asStateFlow()

    private var _products: MutableStateFlow<List<Products>?> =
        MutableStateFlow(null)
    val products: StateFlow<List<Products>?> =
        _products.asStateFlow()

    init {
        getCategories()
    }


    private fun getCategories() {
        launchCoroutine(coroutineExceptionHandler) {

            getCategoriesUseCase()
                .onStart {
                    _state.emit(BaseViewState.Loading)
                }.collectLatest {
                    when (it) {
                        is ResultWrapper.Success -> {
                            _categories.emit(it.data)
                            val categoryId = it.data?.firstOrNull()?.id

                            categoryId?.let { it1 -> getProducts(it1) }
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

    private fun getProducts(categoryId: Int) {
        launchCoroutine(coroutineExceptionHandler) {

            getProductsUseCase(categoryId)
                .onStart {
                    _state.emit(BaseViewState.Loading)
                }.collectLatest {
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

}