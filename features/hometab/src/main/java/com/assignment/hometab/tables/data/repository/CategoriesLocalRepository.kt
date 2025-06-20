package com.assignment.hometab.tables.data.repository

import com.assignment.caching.extensions.tryMapperQuery
import com.assignment.caching.manager.CachingManager
import com.assignment.caching.manager.ProviderEnum
import com.assignment.core.model.ResultWrapper
import com.assignment.hometab.tables.data.mapper.entityToCategoryList
import com.assignment.hometab.tables.data.mapper.toCategoryEntity
import com.assignment.hometab.tables.domain.model.Categories
import com.assignment.hometab.tables.domain.repository.ICategoriesLocalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CategoriesLocalRepository(private val cachingManager: CachingManager) :
    ICategoriesLocalRepository {

    override fun getCategories(): Flow<ResultWrapper<List<Categories>?>> = flow {
        val result = tryMapperQuery({
            cachingManager.getProvider(ProviderEnum.ROOM).getCategories()
        })
        { weather ->
            weather?.entityToCategoryList()
        }
        emit(result)
    }


    override fun insertCategories(categories: List<Categories>?): Flow<ResultWrapper<Unit?>> =
        flow {
            val result = tryMapperQuery({
                categories?.let {
                    cachingManager.getProvider(ProviderEnum.ROOM)
                        .insertCategories(it.toCategoryEntity())
                }
            }) {}
            emit(result)
        }



}
