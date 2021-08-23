package co.cmd.core.data.datasource

import co.cmd.core.domain.Recipe

interface CookDatasource {
    suspend fun getRecipes(actualPage: Int, itemsPerPage: Int):Result<List<Recipe>>
}