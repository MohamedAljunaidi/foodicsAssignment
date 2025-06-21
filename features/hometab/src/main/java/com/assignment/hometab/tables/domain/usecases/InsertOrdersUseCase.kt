package com.assignment.hometab.tables.domain.usecases

import com.assignment.core.bases.BaseUseCase
import com.assignment.core.model.ResultWrapper
import com.assignment.hometab.tables.domain.model.Order
import com.assignment.hometab.tables.domain.repository.IOrdersLocalRepository
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Single

@Single
class InsertOrdersUseCase(
    private val localRepository: IOrdersLocalRepository
) :
    BaseUseCase<Order, Flow<ResultWrapper<Unit?>>> {

    override suspend fun invoke(order: Order?): Flow<ResultWrapper<Unit?>> =
        localRepository.insertOrder(
            order
        )
}