package com.assignment.caching.roomdb.features.orders.entities


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(
    tableName = "order_table",
)
data class OrderEntity(
    @SerializedName("id")
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    @SerializedName("image")
    var image: String?,

    @SerializedName("name")
    var name: String?,

    @SerializedName("description")
    var description: String?,

    @SerializedName("price")
    var price: Double?,

)
