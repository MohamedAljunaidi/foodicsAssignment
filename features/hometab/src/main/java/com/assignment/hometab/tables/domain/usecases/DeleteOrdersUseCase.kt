package com.assignment.hometab.tables.domain.usecases

import com.assignment.core.bases.BaseUseCase
import com.assignment.core.model.ResultWrapper
import com.assignment.hometab.tables.domain.repository.IOrdersLocalRepository
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Single

@Single
class DeleteOrdersUseCase(
    private val localRepository: IOrdersLocalRepository) :
    BaseUseCase<Map<String, String>, Flow<ResultWrapper<Unit?>>> {

    override suspend fun invoke(params: Map<String, String>?): Flow<ResultWrapper<Unit?>> =
        localRepository.deleteOrders()
}