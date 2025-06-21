package com.assignment.search.domain.repository

import com.assignment.core.model.ResultWrapper
import com.assignment.search.domain.model.Products
import kotlinx.coroutines.flow.Flow

interface ISearchLocalRepository {
    fun getProducts(): Flow<ResultWrapper<List<Products>?>>

}