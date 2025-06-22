package com.assignment.hometab.tables.data.repository

import com.assignment.core.model.ResultWrapper
import com.assignment.hometab.tables.data.HomeService
import com.assignment.hometab.tables.data.mapper.toCategories
import com.assignment.hometab.tables.domain.model.Categories
import com.assignment.hometab.tables.domain.repository.ICategoriesRepository
import com.assignment.network.extensions.tryRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CategoriesRemoteRepository(
    private val homeService: HomeService,
) : ICategoriesRepository {

    override fun getCategories(): Flow<ResultWrapper<List<Categories>?>> = flow {
        val result = tryRequest(
            request = {
                homeService.getCategories()
            },
            dataToDomain = { response ->
                response?.toCategories()
            }
        )
        emit(result)
    }

}