package com.assignment.hometab.tables.data

import com.assignment.hometab.tables.data.model.CategoriesResponse
import com.assignment.hometab.tables.data.model.ProductResponse
import com.assignment.network.result.NetworkResult
import com.assignment.network.services.ApiManager

class HomeService(private val apiManager: ApiManager) {

    companion object {
        private const val PATH_GET_CATEGORIES =
            "categories"
        private const val PATH_GET_PRODUCTS =
            "products"

    }

    suspend fun getCategories(
    ): NetworkResult<List<CategoriesResponse>> =
        apiManager.getRequest(
            PATH_GET_CATEGORIES
        )
    suspend fun getProducts(
    ): NetworkResult<List<ProductResponse>> =
        apiManager.getRequest(
            PATH_GET_PRODUCTS
        )

}