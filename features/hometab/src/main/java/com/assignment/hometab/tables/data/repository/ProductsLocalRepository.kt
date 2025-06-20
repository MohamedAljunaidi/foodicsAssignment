package com.assignment.hometab.tables.data.repository

import com.assignment.caching.extensions.tryMapperQuery
import com.assignment.caching.manager.CachingManager
import com.assignment.caching.manager.ProviderEnum
import com.assignment.core.model.ResultWrapper
import com.assignment.hometab.tables.data.mapper.entityToProductList
import com.assignment.hometab.tables.data.mapper.toProductsEntity
import com.assignment.hometab.tables.domain.model.Products
import com.assignment.hometab.tables.domain.repository.IProductsLocalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ProductsLocalRepository(private val cachingManager: CachingManager) :
    IProductsLocalRepository {

    override fun getProducts(categoryId: Int): Flow<ResultWrapper<List<Products>?>> = flow {
        val result = tryMapperQuery({
            cachingManager.getProvider(ProviderEnum.ROOM).getProducts(
                categoryId = categoryId
            )
        })
        { weather ->
            weather?.entityToProductList()
        }
        emit(result)
    }


    override fun insertProducts(products: List<Products>?): Flow<ResultWrapper<Unit?>> =
        flow {
            val result = tryMapperQuery({
                products?.let {
                    cachingManager.getProvider(ProviderEnum.ROOM)
                        .insertProducts(it.toProductsEntity())
                }
            }) {}
            emit(result)
        }



}
