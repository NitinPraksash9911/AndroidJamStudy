package com.example.navcomponent.netwrok

import com.example.navcomponent.datasource.model.FoodRecipe
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipeService {

    @GET("food/search")
    suspend fun searchRecipe(@Query("query") query:String, @Query("number") recipeCount:Int):Response<FoodRecipe>
}