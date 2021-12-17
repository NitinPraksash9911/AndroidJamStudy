package com.example.navcomponent.datasource.localdb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.navcomponent.datasource.model.RecipeResult

@Dao
interface RecipeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert( recipes: List<RecipeResult>)

    @Query("Select * from recipe")
    fun getRecipe(): List<RecipeResult>

    @Query("DELETE FROM recipe")
    fun deleteAllRecipe()

}