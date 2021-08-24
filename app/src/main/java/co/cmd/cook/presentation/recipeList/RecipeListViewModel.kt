package co.cmd.cook.presentation.recipeList

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import co.cmd.cook.framework.datasource.paging.NETWORK_PAGE_SIZE
import co.cmd.cook.framework.datasource.paging.PagingProductsDataSource
import co.cmd.cook.util.NavigationCommand
import co.cmd.core.domain.Recipe
import co.cmd.core.interactors.GetRecipes
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.CONFLATED
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow

class RecipeListViewModel(private val getRecipes: GetRecipes) : ViewModel() {

    private val _navigationActions = Channel<NavigationCommand>(capacity = CONFLATED)

    // Exposed with receiveAsFlow to make sure that only one observer receives updates.
    val navigationActions = _navigationActions.receiveAsFlow()

    val recipeList: Flow<PagingData<Recipe>> =
        Pager(config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false),
            pagingSourceFactory = {
                PagingProductsDataSource(getRecipes)
            }).flow

    fun refresh() {
       recipeList
    }

    fun onRecipeItemClicked(recipe: Recipe) {
        _navigationActions.trySend(
            NavigationCommand.To(
                RecipeListFragmentDirections.actionRecipeListFragmentToRecipeDetailFragment(
                    recipe.id.value
                )
            )
        )
    }
}