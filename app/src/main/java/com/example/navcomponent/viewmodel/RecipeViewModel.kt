package com.example.navcomponent.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.navcomponent.datasource.localdb.RecipeDatabase
import com.example.navcomponent.datasource.model.RecipeResult
import com.example.navcomponent.datasource.repository.RecipeRepository
import com.example.navcomponent.netwrok.RecipeService
import com.example.navcomponent.netwrok.RetrofitFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RecipeViewModel(context: Application) : AndroidViewModel(context) {

    private val repository = RecipeRepository(
        RetrofitFactory.retrofit.create(RecipeService::class.java),
        RecipeDatabase.getInstance(context).getRecipeDao()
    )

    private var mutableLiveData = MutableLiveData<List<RecipeResult>>()

    val foodLiveData: LiveData<List<RecipeResult>>
        get() = mutableLiveData

    fun searchRecipe(query: String, recipeCount: Int = 20) {

        viewModelScope.launch(Dispatchers.IO) {

            try {
                val response = repository.searchRecipe(query, recipeCount)
                repository.insertRecipe(response)
                mutableLiveData.postValue(repository.getRecipeList(query))
            }catch (e:Exception){
                mutableLiveData.postValue(repository.getRecipeList(query))

            }


        }

    }

}