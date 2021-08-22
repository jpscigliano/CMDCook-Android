package co.cmd.core.data.datasource

import co.cmd.core.domain.Recipe

interface CookDatasource {
    suspend fun getRecipes(amountPerPage:Int,page:Int):Result<List<Recipe>>
}