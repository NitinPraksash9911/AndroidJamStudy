package com.example.navcomponent.ui

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.navcomponent.R.layout
import com.example.navcomponent.databinding.FragmentRecipeDetailBinding

class RecipeDetailFrag : Fragment(layout.fragment_recipe_detail) {

    lateinit var binding: FragmentRecipeDetailBinding

    private val args: RecipeDetailFragArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentRecipeDetailBinding.bind(view)

        init(args)

    }

    fun init(recipeArgs: RecipeDetailFragArgs) {

        binding.contentTv.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(recipeArgs.recipe.content, Html.FROM_HTML_MODE_COMPACT)
        } else {
            Html.fromHtml(recipeArgs.recipe.content)
        }

        Glide.with(binding.iv.context).load(recipeArgs.recipe.image).into(binding.iv)

    }

}