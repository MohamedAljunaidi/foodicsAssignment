package com.assignment.search.di

import com.assignment.caching.manager.CachingManager
import com.assignment.search.data.repository.SearchLocalRepository
import com.assignment.search.domain.repository.ISearchLocalRepository
import com.assignment.search.domain.usecases.SearchProductsUseCase
import com.assignment.search.presentations.SearchViewModel
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
class SearchModule {

    @Single
    fun getSearchLocalRepository(cachingManager: CachingManager): ISearchLocalRepository {
        return SearchLocalRepository(cachingManager)
    }

    @Single
    fun provideSearchUseCase(searchLocalRepository: ISearchLocalRepository): SearchProductsUseCase {
        return SearchProductsUseCase(searchLocalRepository)
    }

    @Single
    fun provideSearchViewModel(searchUseCase: SearchProductsUseCase): SearchViewModel =
        SearchViewModel(searchUseCase)

}