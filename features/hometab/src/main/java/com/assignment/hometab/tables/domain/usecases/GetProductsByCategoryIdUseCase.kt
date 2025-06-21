package com.assignment.hometab.tables.domain.usecases

import com.assignment.core.bases.BaseUseCase
import com.assignment.core.extensions.networkBoundResource
import com.assignment.core.extensions.resultWrapperData
import com.assignment.core.model.ResultWrapper
import com.assignment.hometab.tables.domain.model.Products
import com.assignment.hometab.tables.domain.repository.IProductsLocalRepository
import com.assignment.hometab.tables.domain.repository.IProductsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flowOf
import org.koin.core.annotation.Single

@Single
class GetProductsByCategoryIdUseCase(
    private val remoteRepository: IProductsRepository,
    private val localRepository: IProductsLocalRepository
) :
    BaseUseCase<Int, Flow<ResultWrapper<List<Products>?>>> {
    private var response: Flow<ResultWrapper<List<Products>?>> = emptyFlow()

    override suspend fun invoke(params: Int?): Flow<ResultWrapper<List<Products>?>> =
        networkBoundResource(
            queryDb = {
                localRepository.getProductsByCategoryId(params ?: 0)
            },
            fetchApi = {
                remoteRepository.getProduct()
            },
            saveApiResult = { fetchResult ->
                fetchResult.collect { resultWrapper ->
                    this.response = flowOf(resultWrapper)

                    resultWrapperData(resultWrapper, { product ->
                        localRepository.insertProducts(products = product).collect()
                    }, {
                        localRepository.getProductsByCategoryId(params ?: 0)
                    })
                }
            }, onQueryDbError = {
                response

            }
        )
}