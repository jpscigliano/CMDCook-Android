package co.cmd.cook.presentation.recipeList

import android.view.LayoutInflater
import android.view.ViewGroup
import co.cmd.cook.databinding.FragmentRecipeListBinding
import co.cmd.cook.presentation.BaseViewBindingFragment

class RecipeListFragment : BaseViewBindingFragment<FragmentRecipeListBinding>() {
    companion object {

        @JvmStatic
        fun newInstance() = RecipeListFragment()
    }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentRecipeListBinding
        get() = FragmentRecipeListBinding::inflate

    override fun onCreated() {
    }
}