package com.assignment.search.domain.model

import com.google.gson.annotations.SerializedName

data class Products(

    @SerializedName("category")
    var category: Categories?,
    @SerializedName("description")
    var description: String?,
    @SerializedName("id")
    var id: Int?,
    @SerializedName("image")
    var image: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("price")
    var price: Double?,

    )
