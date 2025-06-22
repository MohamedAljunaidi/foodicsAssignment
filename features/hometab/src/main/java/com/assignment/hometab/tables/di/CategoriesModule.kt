package com.assignment.hometab.tables.di

import com.assignment.caching.manager.CachingManager
import com.assignment.hometab.tables.data.HomeService
import com.assignment.hometab.tables.data.repository.CategoriesLocalRepository
import com.assignment.hometab.tables.data.repository.CategoriesRemoteRepository
import com.assignment.hometab.tables.data.repository.OrdersLocalRepository
import com.assignment.hometab.tables.data.repository.ProductsLocalRepository
import com.assignment.hometab.tables.data.repository.ProductsRemoteRepository
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
    fun getCategoriesService(apiManager: ApiManager): HomeService {
        return HomeService(apiManager)
    }

    @Single
    fun getCategoriesRepository(homeService: HomeService): ICategoriesRepository {
        return CategoriesRemoteRepository(homeService)
    }

    @Single
    fun getProductsRepository(homeService: HomeService): IProductsRepository {
        return ProductsRemoteRepository(homeService)
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