package com.assignment.hometab.common

import com.assignment.caching.roomdb.features.categories.entities.CategoryEntity
import com.assignment.caching.roomdb.features.orders.entities.OrderEntity
import com.assignment.caching.roomdb.features.products.entities.ProductsEntity
import com.assignment.core.model.ErrorModel
import com.assignment.core.model.ResultException
import com.assignment.core.model.ResultWrapper
import com.assignment.hometab.tables.data.model.CategoriesResponse
import com.assignment.hometab.tables.data.model.ProductResponse
import com.assignment.hometab.tables.domain.model.Categories
import com.assignment.hometab.tables.domain.model.Order
import com.assignment.hometab.tables.domain.model.Products
import com.assignment.network.exceptions.NetworkException

object TestConstants {

    val RESULT_EXCEPTION = ResultException()

    val CATEGORY_LIST_ENTITY: List<CategoryEntity> =
        listOf(
            CategoryEntity(
                id = 1,
                name = "pizza"
            )
        )

    val CATEGORY_LIST_RESPONSE: List<CategoriesResponse> =
        listOf(
            CategoriesResponse(
                id = 1,
                name = "pizza"
            )
        )
    val API_ERROR =
        NetworkException.ApiErrorException()


    val ORDER_ENTITY: OrderEntity =
        OrderEntity(
            id = 1,
            name = "pizza",
            image = "image",
            description = "description",
            price = 10.0
        )
    val ORDER_LIST_ENTITY: List<OrderEntity> =
        listOf(
            ORDER_ENTITY
        )


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


    val PRODUCT_LIST_RESPONSE: ArrayList<ProductResponse> =
        arrayListOf(
            ProductResponse(
                id = 1,
                name = "pizza",
                image = "image",
                description = "description",
                price = 10.0,
                category = CategoriesResponse(
                    id = 1,
                    name = "pizza"
                )
            )
        )


    val CATEGORY_LIST = arrayListOf(
        Categories(id = 1, name = "Pizza")
    )

    val SUCCESS_CATEGORY_LIST = ResultWrapper.Success(CATEGORY_LIST)

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

    val ORDER_LIST = arrayListOf(
        Order(
            id = 1,
            name = "Pizza",
            image = "image",
            description = "description",
            price = 10.0
        )
    )

    val SUCCESS_ORDER_LIST = ResultWrapper.Success(ORDER_LIST)

    val ERROR_RESPONSE =
        ResultWrapper.Error(ResultException(errorModel = ErrorModel(errorDescription = "error")))

    const val EXCEPTION = "EXCEPTION"

}