package com.assignment.core.bases


/**
 *  Main base [BaseUseCase]
 *
 *
 *  @param Parameter the type of input data
 *  @param Result the type of output data
 */
interface BaseUseCase<in Parameter, out Result> {
    suspend operator fun invoke(params: Parameter?=null): Result
}
