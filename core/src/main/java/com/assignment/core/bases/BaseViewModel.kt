package com.assignment.core.bases

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assignment.core.model.ErrorModel
import com.assignment.core.model.ResultException
import com.assignment.core.model.ServerExceptionType
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 *
 * Main base [BaseViewModel]
 */
abstract class BaseViewModel : ViewModel() {

    val state: StateFlow<BaseViewState>
        get() = _state

    var _state =
        MutableStateFlow<BaseViewState>(BaseViewState.Idle)

    /**
     * The [launchCoroutine]
     *
     * handle the coroutine viewModelScope with coroutineExceptionHandler
     */

    open val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        viewModelScope.launch {
            _state.emit(
                BaseViewState.Error(
                    ResultException(
                        errorModel = ErrorModel(
                            cause = exception,
                            errorType = ServerExceptionType.UnExpectedError
                        )
                    )
                )
            )
        }
    }

    fun launchCoroutine(
        coroutineExceptionHandler: CoroutineExceptionHandler,
        block: suspend CoroutineScope.() -> Unit
    ): Job {
        return viewModelScope.launch(coroutineExceptionHandler) {
            block()
        }
    }
}
