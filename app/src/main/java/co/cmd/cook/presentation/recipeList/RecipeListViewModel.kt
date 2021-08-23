package co.cmd.cook.presentation.recipeList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import co.cmd.cook.framework.datasource.paging.NETWORK_PAGE_SIZE
import co.cmd.cook.framework.datasource.paging.PagingProductsDataSource
import co.cmd.core.domain.Recipe
import co.cmd.core.interactors.GetRecipes
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RecipeListViewModel(private val getRecipes: GetRecipes) : ViewModel() {

    val recipeList: Flow<PagingData<Recipe>> = Pager(config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false),
        pagingSourceFactory = {
            PagingProductsDataSource(getRecipes)
        }).flow

}