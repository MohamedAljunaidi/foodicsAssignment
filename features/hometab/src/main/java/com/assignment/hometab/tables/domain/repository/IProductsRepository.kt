package com.assignment.hometab.tables.domain.repository

import com.assignment.core.model.ResultWrapper
import com.assignment.hometab.tables.domain.model.Products
import kotlinx.coroutines.flow.Flow

interface IProductsRepository {
    fun getProduct(): Flow<ResultWrapper<List<Products>?>>
}