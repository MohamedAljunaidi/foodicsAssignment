package com.assignment.hometab.tables.data.repository

import com.assignment.core.model.ResultWrapper
import com.assignment.hometab.tables.data.HomeService
import com.assignment.hometab.tables.data.mapper.toProducts
import com.assignment.hometab.tables.domain.model.Products
import com.assignment.hometab.tables.domain.repository.IProductsRepository
import com.assignment.network.extensions.tryRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ProductsRemoteRepository(
    private val homeService: HomeService,
) : IProductsRepository {

    override fun getProduct(): Flow<ResultWrapper<List<Products>?>> = flow {
        val result = tryRequest(
            request = {
                homeService.getProducts()
            },
            dataToDomain = { response ->
                response?.toProducts()
            }
        )
        emit(result)
    }

}