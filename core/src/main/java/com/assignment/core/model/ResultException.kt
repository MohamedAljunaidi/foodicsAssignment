package com.assignment.core.model

open class ResultException(
    val errorModel: ErrorModel? = null,
): RuntimeException(errorModel?.cause)