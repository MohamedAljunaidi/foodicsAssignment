package com.assignment.hometab.tables.domain.usecases

import com.assignment.core.bases.BaseUseCase
import com.assignment.core.extensions.networkBoundResource
import com.assignment.core.extensions.resultWrapperData
import com.assignment.core.model.ResultWrapper
import com.assignment.hometab.tables.domain.model.Categories
import com.assignment.hometab.tables.domain.repository.ICategoriesLocalRepository
import com.assignment.hometab.tables.domain.repository.ICategoriesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flowOf
import org.koin.core.annotation.Single

@Single
class GetCategoriesUseCase(
    private val remoteRepository: ICategoriesRepository,
    private val localRepository: ICategoriesLocalRepository,

    ) :
    BaseUseCase<Map<String, String>, Flow<ResultWrapper<List<Categories>?>>> {
    private var response: Flow<ResultWrapper<List<Categories>?>> = emptyFlow()

    override suspend fun invoke(params: Map<String, String>?): Flow<ResultWrapper<List<Categories>?>> =
        networkBoundResource(
            queryDb = {
                localRepository.getCategories()
            },
            fetchApi = {
                remoteRepository.getCategories()
            },
            saveApiResult = { fetchResult ->
                fetchResult.collect { resultWrapper ->
                    this.response = flowOf(resultWrapper)

                    resultWrapperData(resultWrapper, { categories ->
                        localRepository.insertCategories(categories = categories).collect()
                    }, {
                        localRepository.getCategories()
                    })
                }
            }, onQueryDbError = {
                response
            }
        )
}