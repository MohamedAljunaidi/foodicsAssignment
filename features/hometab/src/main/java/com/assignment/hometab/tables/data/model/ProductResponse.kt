package com.assignment.hometab.tables.data.model


import com.google.gson.annotations.SerializedName

data class ProductResponse(
        @SerializedName("category")
        var category: CategoriesResponse?,
        @SerializedName("description")
        var description: String?,
        @SerializedName("id")
        var id: Int?,
        @SerializedName("image")
        var image: String?,
        @SerializedName("name")
        var name: String?,
        @SerializedName("price")
        var price: Double?
    )
