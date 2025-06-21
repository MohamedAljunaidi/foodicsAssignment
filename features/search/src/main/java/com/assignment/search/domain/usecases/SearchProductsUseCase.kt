package com.assignment.search.domain.usecases

import com.assignment.core.bases.BaseUseCase
import com.assignment.core.model.ResultWrapper
import com.assignment.search.domain.model.Products
import com.assignment.search.domain.repository.ISearchLocalRepository
import kotlinx.coroutines.flow.Flow

class SearchProductsUseCase(
    private val localRepository: ISearchLocalRepository
) :
    BaseUseCase<Map<String, String>, Flow<ResultWrapper<List<Products>?>>> {

    override suspend fun invoke(params: Map<String, String>?): Flow<ResultWrapper<List<Products>?>> =
        localRepository.getProducts()
}