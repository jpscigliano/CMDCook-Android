package co.cmd.cook.presentation.recipeList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import co.cmd.cook.databinding.FragmentRecipeListBinding
import co.cmd.cook.presentation.BaseViewBindingFragment
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel

class RecipeListFragment : BaseViewBindingFragment<FragmentRecipeListBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentRecipeListBinding
        get() = FragmentRecipeListBinding::inflate
    private val vm: RecipeListViewModel by viewModel()
    private lateinit var adapter: RecipeListAdapter

    override fun onCreated() {
        adapter = RecipeListAdapter()
        adapter.recipeClickListener = { recipe ->
            vm.onRecipeItemClicked(recipe)
        }
        setupUI()
        setupObservers()
    }

    private fun setupUI() {
        binding.recyclerRecipeList.adapter = adapter
        binding.recyclerRecipeList.layoutManager = GridLayoutManager(requireContext(), 1)
        binding.recyclerRecipeList.setHasFixedSize(true)
    }

    private fun setupObservers() {
        lifecycleScope.launchWhenStarted {
            vm.recipeList.collectLatest { pagingData ->
                adapter.submitData(pagingData)
            }
        }

    }
}