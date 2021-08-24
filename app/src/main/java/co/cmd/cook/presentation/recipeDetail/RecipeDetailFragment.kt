package co.cmd.cook.presentation.recipeDetail

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import co.cmd.cook.R
import co.cmd.cook.databinding.FragmentRecipeDetailBinding
import co.cmd.cook.presentation.BaseViewBindingFragment
import co.cmd.cook.presentation.recipeDetail.RecipeDetailViewModel.Event.ShowError
import co.cmd.core.domain.ID
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel

class RecipeDetailFragment : BaseViewBindingFragment<FragmentRecipeDetailBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentRecipeDetailBinding
        get() = FragmentRecipeDetailBinding::inflate

    private val arg: RecipeDetailFragmentArgs by navArgs()
    private val recipeDetailViewModel: RecipeDetailViewModel by viewModel()

    override fun onCreated() {
        recipeDetailViewModel.fetchData(ID(arg.recipeID))
        setupObservers()
    }

    private fun setupObservers() {
        lifecycleScope.launchWhenStarted {
            recipeDetailViewModel.recipeDetailViewState.collect {
                it.recipeDetail?.let { recipe ->
                    binding.textName.text = recipe.name.value
                    binding.textPrepTime.text =
                        String.format(getString(R.string.preparation_time), recipe.preparationTime.value)
                    binding.textCookTime.text =
                        String.format(getString(R.string.cooking_time), recipe.cookingTime.value)
                    binding.textDescription.text = recipe.name.value
                    Glide.with(requireContext()).applyDefaultRequestOptions(
                        RequestOptions()
                            .placeholder(R.drawable.menu_placeholder)
                            .error(R.drawable.menu_placeholder)
                    )
                        .load(recipe.imageUrl.value)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .into(binding.imageRecipe)
                }
            }
        }
        lifecycleScope.launchWhenStarted {
            recipeDetailViewModel.loadingViewState.collect { isLoading ->
                if (isLoading) {
                    binding.progressbar.show()
                } else {
                    binding.progressbar.hide()
                }

            }
        }
        lifecycleScope.launchWhenStarted {
            recipeDetailViewModel.messageEvents.collect { event ->
                //show loading
                when (event) {
                    is ShowError -> Toast.makeText(context, event.error, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() = RecipeDetailFragment()
    }
}