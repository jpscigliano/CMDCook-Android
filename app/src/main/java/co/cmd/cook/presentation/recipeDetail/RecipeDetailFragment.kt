package co.cmd.cook.presentation.recipeDetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import co.cmd.cook.databinding.FragmentRecipeDetailBinding
import co.cmd.cook.presentation.BaseViewBindingFragment

class RecipeDetailFragment : BaseViewBindingFragment<FragmentRecipeDetailBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentRecipeDetailBinding
        get() = FragmentRecipeDetailBinding::inflate


    override fun onCreated() {

    }

    companion object {

        @JvmStatic
        fun newInstance() = RecipeDetailFragment()
    }
}