package co.cmd.cook.framework.datasource.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import co.cmd.core.domain.Recipe
import co.cmd.core.interactors.GetRecipes

const val NETWORK_PAGE_SIZE = 10
private const val STARTING_PAGE_INDEX = 1

class PagingProductsDataSource(
    private val getRecipes: GetRecipes
) : PagingSource<Int, Recipe>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Recipe> {
        val pageIndex = params.key ?: STARTING_PAGE_INDEX
        //Fetch Products
        val result = getRecipes(actualPage = pageIndex, itemsPerPage = NETWORK_PAGE_SIZE)
        if (result.isSuccess) {
            val nextKey = if (result.getOrNull().isNullOrEmpty()) {
                null
            } else {
                pageIndex + 1
            }
            return LoadResult.Page(
                data = result.getOrNull() ?: listOf(),
                prevKey = null,
                nextKey = nextKey
            )
        } else {
            return LoadResult.Error(result.exceptionOrNull() ?: Throwable("Unknown Error"))
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Recipe>): Int? {
        val key=state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
        return key
    }
}



