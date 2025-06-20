package com.assignment.hometab.tables.domain.repository

import com.assignment.core.model.ResultWrapper
import com.assignment.hometab.tables.domain.model.Categories
import kotlinx.coroutines.flow.Flow

interface ICategoriesRepository {
    fun getCategories(): Flow<ResultWrapper<List<Categories>?>>
}