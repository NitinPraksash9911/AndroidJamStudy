package com.example.navcomponent.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.navcomponent.R.layout
import com.example.navcomponent.databinding.FragmentHomeBinding
import com.example.navcomponent.viewmodel.RecipeViewModel

class HomeFragment : Fragment(layout.fragment_home) {

    private val recipeViewModel: RecipeViewModel by activityViewModels()

    lateinit var recipeAdapter: RecipeAdapter

    lateinit var binding: FragmentHomeBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentHomeBinding.bind(view)

        init()

    }

    private fun init() {

        recipeViewModel.searchRecipe("Milk")

        recipeAdapter = RecipeAdapter {

            recipeViewModel.setRecipeData(it)

            val direction = HomeFragmentDirections.actionHomeFragmentToRecipeDetailFrag()

            findNavController().navigate(direction)

        }

        binding.recipeRv.adapter = recipeAdapter

        recipeViewModel.foodLiveData.observe(viewLifecycleOwner) {

            recipeAdapter.submitList(it.searchResults[0].recipeResults)

        }

    }

}