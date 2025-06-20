package com.assignment.caching.roomdb.features.categories.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.assignment.caching.roomdb.common.BaseDao
import com.assignment.caching.roomdb.features.categories.entities.CategoryEntity

@Dao
interface CategoriesDao : BaseDao<CategoryEntity> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategories(obj: List<CategoryEntity>)

    @Query("SELECT * FROM category_table")
    fun getCategories(): List<CategoryEntity>

    @Query("DELETE FROM category_table")
    fun deleteAllData()

}
