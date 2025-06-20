package com.assignment.hometab.tables.data.mapper

import com.assignment.hometab.tables.data.model.CategoriesResponse
import com.assignment.hometab.tables.data.model.ProductResponse
import com.assignment.hometab.tables.domain.model.Categories
import com.assignment.hometab.tables.domain.model.Products
import kotlin.collections.map

internal fun List<CategoriesResponse>.toCategories(): List<Categories> {
    val dataList = arrayListOf<Categories>()
    this.map {
        dataList.add(
            it.toCategory()
        )
    }

    return dataList

}

internal fun CategoriesResponse.toCategory(): Categories {
    return Categories(
        id = this.id,
        name = this.name ?: ""
    )

}


internal fun List<ProductResponse>.toProducts(): List<Products> {
    val dataList = arrayListOf<Products>()
    this.map { product ->
        product.category?.let {
            dataList.add(
                Products(
                    id = product.id,
                    name = product.name ?: "",
                    description = product.description ?: "",
                    price = product.price,
                    image = product.image ?: "",
                    category = it.toCategory(),

                    )
            )
        }
    }

    return dataList

}



