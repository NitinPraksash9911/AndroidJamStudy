package com.example.navcomponent.datasource.model

import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class FoodRecipe(

    @SerializedName("searchResults")
    val searchResults: List<SearchResult>,
    @SerializedName("totalResults")
    val totalResults: Int
) {

    data class SearchResult(
        @SerializedName("name")
        val name: String,
        @SerializedName("results")
        val recipeResults: List<RecipeResult>,
        @SerializedName("totalResults")
        val totalResults: Int,
        @SerializedName("totalResultsVariants")
        val totalResultsVariants: Int,
        @SerializedName("type")
        val type: String
    ) {

        @Parcelize
        data class RecipeResult(
            @SerializedName("content")
            val content: String?,
            @SerializedName("id")
            val id: Int,
            @SerializedName("image")
            val image: String,
            @SerializedName("name")
            val name: String,
        ) : Parcelable {

            object ResultDiffUtil : DiffUtil.ItemCallback<RecipeResult>() {

                override fun areItemsTheSame(oldItem: RecipeResult, newItem: RecipeResult): Boolean {
                    return oldItem.id == newItem.id

                }

                override fun areContentsTheSame(oldItem: RecipeResult, newItem: RecipeResult): Boolean {

                    return oldItem.id == newItem.id && oldItem.name == newItem.name && oldItem.content == newItem.content
                }

            }

        }

    }
}