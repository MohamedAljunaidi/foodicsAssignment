package com.assignment.search.data.mapper

import com.assignment.caching.roomdb.features.categories.entities.CategoryEntity
import com.assignment.caching.roomdb.features.products.entities.ProductsEntity
import com.assignment.search.domain.model.Categories
import com.assignment.search.domain.model.Products


internal fun CategoryEntity.entityToCategoryItem(): Categories {
    return Categories(
        id = this.id,
        name = this.name,
    )
}

internal fun List<ProductsEntity>.entityToProductList(): List<Products> {
    val dataList = arrayListOf<Products>()
    this.map {
        dataList.add(
            Products(
                id = it.id,
                name = it.name,
                description = it.description,
                price = it.price,
                image = it.image,
                category = it.category?.entityToCategoryItem(),

                )
        )
    }
    return dataList
}




