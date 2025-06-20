package com.assignment.caching.roomdb.features.categories.entities


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "category_table")
data  class CategoryEntity(

    @SerializedName("id")
    @PrimaryKey
    var id: Int,

    @SerializedName("name")
    var name: String? = "",

)
