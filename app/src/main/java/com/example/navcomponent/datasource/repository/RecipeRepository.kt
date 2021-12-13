package com.example.navcomponent.datasource.repository

import com.example.navcomponent.netwrok.RecipeService
import com.example.navcomponent.netwrok.RetrofitFactory

class RecipeRepository(private val retrofitFactory: RetrofitFactory) {

    suspend fun searchRecipe(query: String, recipeCount: Int) =
        retrofitFactory.retrofit.create(RecipeService::class.java).searchRecipe(query, recipeCount)


}