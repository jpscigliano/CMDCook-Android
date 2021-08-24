package co.cmd.cook.presentation.recipeList

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.paging.LoadState.Error
import androidx.paging.LoadState.Loading
import androidx.paging.LoadState.NotLoading
import androidx.recyclerview.widget.GridLayoutManager
import co.cmd.cook.databinding.FragmentRecipeListBinding
import co.cmd.cook.presentation.BaseViewBindingFragment
import co.cmd.cook.util.NavigationCommand.Back
import co.cmd.cook.util.NavigationCommand.BackTo
import co.cmd.cook.util.NavigationCommand.To
import co.cmd.cook.util.NavigationCommand.ToRoot
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

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
        adapter.addLoadStateListener { state ->
            //binding.progressBar.isGone = state.refresh !is LoadState.Loading
            if (state.refresh is LoadState.Error) {
                val error = (state.refresh as LoadState.Error).error
                Timber.e(error)
                Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
            }
            when(state.refresh){
                is NotLoading -> { binding.swipeRefresh.isRefreshing=false}
                Loading -> {}
                is Error -> { binding.swipeRefresh.isRefreshing=false}
            }

        }
        binding.swipeRefresh.setOnRefreshListener {
            adapter.refresh()
        }
    }

    private fun setupObservers() {
        lifecycleScope.launchWhenStarted {
            vm.recipeList.collectLatest { pagingData ->
                adapter.submitData(pagingData)
            }
        }
        lifecycleScope.launchWhenStarted {
            vm.navigationActions.collectLatest { navCommand ->
                when (navCommand) {
                    is To -> findNavController().navigate(navCommand.directions)
                    is Back -> {
                    }
                    is BackTo -> {
                    }
                    is ToRoot -> {
                    }
                }
            }
        }
    }
}