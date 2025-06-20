package com.assignment.hometab.tables.data.repository

import com.assignment.core.model.ResultWrapper
import com.assignment.hometab.tables.data.CategoriesService
import com.assignment.hometab.tables.data.mapper.toCategories
import com.assignment.hometab.tables.domain.model.Categories
import com.assignment.hometab.tables.domain.repository.ICategoriesRepository
import com.assignment.network.extensions.tryRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CategoriesRepository(
    private val categoriesService: CategoriesService,
) : ICategoriesRepository {

    override fun getCategories(): Flow<ResultWrapper<List<Categories>?>> = flow {
        val result = tryRequest(
            request = {
                categoriesService.getCategories()
            },
            dataToDomain = { response ->
                response?.toCategories()
            }
        )
        emit(result)
    }

}