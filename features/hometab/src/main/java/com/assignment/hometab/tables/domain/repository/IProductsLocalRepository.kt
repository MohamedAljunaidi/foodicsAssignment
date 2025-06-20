package com.assignment.hometab.tables.domain.repository

import com.assignment.core.model.ResultWrapper
import com.assignment.hometab.tables.domain.model.Products
import kotlinx.coroutines.flow.Flow

interface IProductsLocalRepository {
    fun getProducts(categoryId: Int): Flow<ResultWrapper<List<Products>?>>
    fun insertProducts(products: List<Products>?): Flow<ResultWrapper<Unit?>>

}