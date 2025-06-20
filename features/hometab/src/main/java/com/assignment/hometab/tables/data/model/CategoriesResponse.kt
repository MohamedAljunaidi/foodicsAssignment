package com.assignment.hometab.tables.data.model


import com.google.gson.annotations.SerializedName

data class CategoriesResponse(
        @SerializedName("id")
        var id: Int?,
        @SerializedName("name")
        var name: String?
    )
