package com.assignment.hometab.tables.data.mapper

import com.assignment.caching.roomdb.features.categories.entities.CategoryEntity
import com.assignment.caching.roomdb.features.products.entities.ProductsEntity
import com.assignment.hometab.tables.domain.model.Categories
import com.assignment.hometab.tables.domain.model.Products


internal fun List<CategoryEntity>.entityToCategoryList(): List<Categories> {
    val dataList = arrayListOf<Categories>()
    this.map {
        dataList.add(
            it.entityToCategoryItem()
        )
    }
    return dataList
}

internal fun CategoryEntity.entityToCategoryItem(): Categories {
    return Categories(
        id = this.id,
        name = this.name,
    )
}

internal fun Categories.toCategoryItemEntity(): CategoryEntity {
    return CategoryEntity(
        id = this.id ?: 0,
        name = this.name,
    )
}


internal fun List<Categories>.toCategoryEntity(): List<CategoryEntity> {
    val dataList = arrayListOf<CategoryEntity>()

    this.map {
        dataList.add(
            it.toCategoryItemEntity()
        )
    }
    return dataList
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


internal fun List<Products>.toProductsEntity(): List<ProductsEntity> {
    val dataList = arrayListOf<ProductsEntity>()

    this.map {
        dataList.add(
            ProductsEntity(
                id = it.id,
                name = it.name,
                description = it.description,
                price = it.price,
                image = it.image,
                category = it.category?.toCategoryItemEntity(),
                categoryId = it.category?.id ?: 0
            )
        )
    }
    return dataList
}
