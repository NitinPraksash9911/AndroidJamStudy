package com.example.navcomponent.datasource.repository

import com.example.navcomponent.datasource.localdb.RecipeDao
import com.example.navcomponent.datasource.model.RecipeResult
import com.example.navcomponent.netwrok.RecipeService

class RecipeRepository(private val apiService: RecipeService, private val recipeDao: RecipeDao) {

    suspend fun searchRecipe(query: String, recipeCount: Int): List<RecipeResult> {
        return apiService.searchRecipe(query, recipeCount).body()!!.searchResults[0].recipeResults

    }

    fun insertRecipe(recipes: List<RecipeResult>) {

        recipeDao.insert(recipes)
    }

    fun delete() {
        recipeDao.deleteAllRecipe()
    }

    fun getRecipeList(name: String): List<RecipeResult> {
        return recipeDao.getRecipe()
    }
}