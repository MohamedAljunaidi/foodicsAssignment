package com.assignment.search.presentations

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.assignment.core.bases.BaseViewModel
import com.assignment.core.bases.BaseViewState
import com.assignment.core.model.ResultException
import com.assignment.core.model.ResultWrapper
import com.assignment.search.domain.model.Products
import com.assignment.search.domain.usecases.SearchProductsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class SearchViewModel(
    private val searchUseCase: SearchProductsUseCase,
) : BaseViewModel() {


    private var _products: MutableStateFlow<List<Products>?> =
        MutableStateFlow(null)


    private val _filteredProducts = mutableStateOf<List<Products>>(emptyList())
    val filteredProducts: State<List<Products>> = _filteredProducts

    private val _searchQuery = mutableStateOf("")
    val searchQuery: State<String> = _searchQuery


    init {
        getAllProducts()
    }



    fun setSearchQuery(query: String) {
        _searchQuery.value = query
        filterProducts()
    }

    private fun filterProducts() {
        if (_searchQuery.value.isEmpty()) {
            _filteredProducts.value = emptyList()
            return
        }
        val query = _searchQuery.value.lowercase()
        _filteredProducts.value = _products.value?.filter { item ->
            item.name?.contains(query, ignoreCase = true) == true
        } ?: emptyList()
    }

    fun getAllProducts() {
        launchCoroutine(coroutineExceptionHandler) {

            searchUseCase().onStart {
                _state.emit(BaseViewState.Loading)
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

}