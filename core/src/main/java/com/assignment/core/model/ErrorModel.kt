package com.assignment.core.model

data class ErrorModel(
    var errorType: ServerExceptionType?=null,
    var errorDescription: String?=null,
    var cause: Throwable?=null,
)
