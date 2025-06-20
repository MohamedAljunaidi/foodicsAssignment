package com.assignment.hometab.tables.data.repository

import com.assignment.core.model.ResultWrapper
import com.assignment.hometab.tables.data.CategoriesService
import com.assignment.hometab.tables.data.mapper.toProducts
import com.assignment.hometab.tables.domain.model.Products
import com.assignment.hometab.tables.domain.repository.IProductsRepository
import com.assignment.network.extensions.tryRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ProductsRepository(
    private val categoriesService: CategoriesService,
) : IProductsRepository {

    override fun getProduct(): Flow<ResultWrapper<List<Products>?>> = flow {
        val result = tryRequest(
            request = {
                categoriesService.getProducts()
            },
            dataToDomain = { response ->
                response?.toProducts()
            }
        )
        emit(result)
    }

}