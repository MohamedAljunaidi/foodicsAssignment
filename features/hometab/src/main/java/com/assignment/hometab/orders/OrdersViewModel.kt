package com.assignment.hometab.orders

import com.assignment.core.bases.BaseViewModel
import com.assignment.core.bases.BaseViewState
import com.assignment.core.model.ResultException
import com.assignment.core.model.ResultWrapper
import com.assignment.hometab.tables.domain.model.Order
import com.assignment.hometab.tables.domain.usecases.DeleteOrderItemUseCase
import com.assignment.hometab.tables.domain.usecases.GetOrdersUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class OrdersViewModel(
    private val getOrdersUseCase: GetOrdersUseCase,
    private val deleteOrderItemUseCase: DeleteOrderItemUseCase,
) :
    BaseViewModel() {

    private var _orders: MutableStateFlow<List<Order>?> =
        MutableStateFlow(null)
    val orders: StateFlow<List<Order>?> =
        _orders.asStateFlow()


     fun getOrders() {
        launchCoroutine(coroutineExceptionHandler) {

            getOrdersUseCase()
                .onStart {
                    _state.emit(BaseViewState.Loading)
                }.collectLatest {
                    when (it) {
                        is ResultWrapper.Success -> {
                            _orders.emit(it.data)
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


     fun deleteOrderItem(order: Order) {
        launchCoroutine(coroutineExceptionHandler) {

            deleteOrderItemUseCase(order)
                .onStart {
                    _state.emit(BaseViewState.Loading)
                }.collectLatest {
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
}