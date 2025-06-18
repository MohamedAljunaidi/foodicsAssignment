package com.assignment.core.bases

import com.assignment.core.model.ResultException


sealed class BaseViewState  {

    /**
     * Idle
     */
    object Idle : BaseViewState()

    /**
     * Loading
     */
    object Loading : BaseViewState()

    /**
     * Error when loading data.
     */
    data class Error(val resultException: ResultException) : BaseViewState()

    /**
     * Data successfully loaded.
     */
    object DataLoaded : BaseViewState()

}
