package com.assignment.hometab.tables.di

import com.assignment.caching.manager.CachingManager
import com.assignment.hometab.tables.data.CategoriesService
import com.assignment.hometab.tables.data.repository.CategoriesLocalRepository
import com.assignment.hometab.tables.data.repository.CategoriesRepository
import com.assignment.hometab.tables.data.repository.OrdersLocalRepository
import com.assignment.hometab.tables.data.repository.ProductsLocalRepository
import com.assignment.hometab.tables.data.repository.ProductsRepository
import com.assignment.hometab.tables.domain.repository.ICategoriesLocalRepository
import com.assignment.hometab.tables.domain.repository.ICategoriesRepository
import com.assignment.hometab.tables.domain.repository.IOrdersLocalRepository
import com.assignment.hometab.tables.domain.repository.IProductsLocalRepository
import com.assignment.hometab.tables.domain.repository.IProductsRepository
import com.assignment.network.services.ApiManager
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
@ComponentScan("com.assignment.hometab.di")
class CategoriesModule {

    @Single
    fun getCategoriesService(apiManager: ApiManager): CategoriesService {
        return CategoriesService(apiManager)
    }

    @Single
    fun getCategoriesRepository(categoriesService: CategoriesService): ICategoriesRepository {
        return CategoriesRepository(categoriesService)
    }

    @Single
    fun getProductsRepository(categoriesService: CategoriesService): IProductsRepository {
        return ProductsRepository(categoriesService)
    }

    @Single
    fun getProductsLocalRepository(cachingManager: CachingManager): IProductsLocalRepository {
        return ProductsLocalRepository(cachingManager)
    }

    @Single
    fun getCategoriesLocalRepository(cachingManager: CachingManager): ICategoriesLocalRepository {
        return CategoriesLocalRepository(cachingManager)
    }

    @Single
    fun getOrderLocalRepository(cachingManager: CachingManager): IOrdersLocalRepository {
        return OrdersLocalRepository(cachingManager)
    }


}