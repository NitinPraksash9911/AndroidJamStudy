package com.example.navcomponent.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.navcomponent.databinding.RecipeItemViewBinding
import com.example.navcomponent.datasource.model.FoodRecipe.SearchResult.RecipeResult
import com.example.navcomponent.ui.RecipeAdapter.RecipeViewHolder

class RecipeAdapter(private var itemClick: (recipeResult: RecipeResult) -> Unit) :
    ListAdapter<RecipeResult, RecipeViewHolder>(RecipeResult.ResultDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {

        val binding = RecipeItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return RecipeViewHolder(binding, itemClick)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {

        val data = getItem(position)
        holder.bindData(data)

    }

    class RecipeViewHolder(private var binding: RecipeItemViewBinding, private var itemClick: (recipeResult: RecipeResult) -> Unit) :
        ViewHolder(binding.root) {

        fun bindData(recipeResult: RecipeResult) {
            binding.recipeNameTv.text = recipeResult.name
            Glide.with(binding.recipeIv.context).load(recipeResult.image).into(binding.recipeIv)

            binding.parentLayout.setOnClickListener {

                itemClick(recipeResult)

            }

        }

    }
}