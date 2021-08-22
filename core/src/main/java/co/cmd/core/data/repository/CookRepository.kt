package co.cmd.core.data.repository

import co.cmd.core.data.datasource.CookDatasource
import co.cmd.core.domain.Recipe

class CookRepository(private val networkCookDatasource: CookDatasource) {

   suspend fun getRecipes(amount: Int, page: Int): Result<List<Recipe>> = networkCookDatasource.getRecipes(amount, page)
}