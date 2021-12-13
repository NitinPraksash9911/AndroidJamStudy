package com.example.navcomponent.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.navcomponent.datasource.model.FoodRecipe
import com.example.navcomponent.datasource.model.FoodRecipe.SearchResult.RecipeResult
import com.example.navcomponent.datasource.repository.RecipeRepository
import com.example.navcomponent.netwrok.RetrofitFactory
import kotlinx.coroutines.launch

class RecipeViewModel : ViewModel() {

    private val repository = RecipeRepository(RetrofitFactory)

    private var mutableLiveData = MutableLiveData<FoodRecipe>()

    val foodLiveData: LiveData<FoodRecipe>
        get() = mutableLiveData

    private var detailLiveData = MutableLiveData<RecipeResult>()
    val recipeData: LiveData<RecipeResult>
        get() = detailLiveData

    fun setRecipeData(recipeResult: RecipeResult) {
        detailLiveData.value = recipeResult
    }

    fun searchRecipe(query: String, recipeCount: Int = 20) {

        viewModelScope.launch {

            val response = repository.searchRecipe(query, recipeCount)

            mutableLiveData.value = response.body()
        }

    }

}