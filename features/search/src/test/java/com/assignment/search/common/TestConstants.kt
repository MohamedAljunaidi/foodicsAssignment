package com.assignment.search.common

import com.assignment.caching.roomdb.features.categories.entities.CategoryEntity
import com.assignment.caching.roomdb.features.products.entities.ProductsEntity
import com.assignment.core.model.ErrorModel
import com.assignment.core.model.ResultException
import com.assignment.core.model.ResultWrapper
import com.assignment.search.domain.model.Categories
import com.assignment.search.domain.model.Products

object TestConstants {

    val RESULT_EXCEPTION = ResultException()


    val PRODUCT_LIST_ENTITY: List<ProductsEntity> =
        listOf(
            ProductsEntity(
                id = 1,
                name = "pizza",
                image = "image",
                description = "description",
                price = 10.0,
                category = CategoryEntity(
                    id = 1,
                    name = "pizza"
                ),
                categoryId = 1
            )
        )


    val PRODUCT = Products(
        id = 1,
        name = "Pizza",
        image = "image",
        description = "description",
        price = 10.0,
        category = Categories(id = 1, name = "Pizza")
    )


    val PRODUCT_LIST = arrayListOf(PRODUCT)

    val SUCCESS_PRODUCT_LIST = ResultWrapper.Success(PRODUCT_LIST)


    val ERROR_RESPONSE =
        ResultWrapper.Error(ResultException(errorModel = ErrorModel(errorDescription = "error")))

    const val EXCEPTION = "EXCEPTION"

}