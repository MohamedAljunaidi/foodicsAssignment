package com.assignment.caching.roomdb.features.products.entities


import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.assignment.caching.roomdb.features.categories.entities.CategoryEntity
import com.assignment.caching.roomdb.features.products.converter.CategoryConverter
import com.google.gson.annotations.SerializedName

@Entity(
    tableName = "products_table",
    foreignKeys = [
        ForeignKey(
            entity = CategoryEntity::class,
            parentColumns = ["id"],
            childColumns = ["categoryId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class ProductsEntity(
    @SerializedName("category")
    @field:TypeConverters(CategoryConverter::class)
    var category: CategoryEntity?,
    @SerializedName("description")
    var description: String?,
    @SerializedName("id")
    @PrimaryKey
    var id: Int?,
    @SerializedName("image")
    var image: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("price")
    var price: Double?,

    val categoryId: Int
)
