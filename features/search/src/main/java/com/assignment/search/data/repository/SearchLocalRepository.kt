package com.assignment.search.data.repository

import com.assignment.caching.extensions.tryMapperQuery
import com.assignment.caching.manager.CachingManager
import com.assignment.caching.manager.ProviderEnum
import com.assignment.core.model.ResultWrapper
import com.assignment.search.data.mapper.entityToProductList
import com.assignment.search.domain.model.Products
import com.assignment.search.domain.repository.ISearchLocalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchLocalRepository(private val cachingManager: CachingManager) :
    ISearchLocalRepository {

    override fun getProducts(): Flow<ResultWrapper<List<Products>?>> = flow {
        val result = tryMapperQuery({
            cachingManager.getProvider(ProviderEnum.ROOM).getAllProducts()
        })
        { data ->
            data?.entityToProductList()
        }
        emit(result)
    }


}
