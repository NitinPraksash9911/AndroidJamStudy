package com.example.navcomponent.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.navcomponent.R.layout
import com.example.navcomponent.databinding.FragmentRecipeDetailBinding
import com.example.navcomponent.datasource.model.FoodRecipe.SearchResult.RecipeResult
import com.example.navcomponent.viewmodel.RecipeViewModel

class RecipeDetailFrag : Fragment(layout.fragment_recipe_detail) {

    lateinit var binding: FragmentRecipeDetailBinding
    private val recipeViewModel: RecipeViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentRecipeDetailBinding.bind(view)

        recipeViewModel.recipeData.observe(viewLifecycleOwner) {

            init(it)
        }

    }

    fun init(recipe: RecipeResult) {

        binding.contentTv.text = recipe.content

        Glide.with(binding.iv.context).load(recipe.image).into(binding.iv)

    }

}