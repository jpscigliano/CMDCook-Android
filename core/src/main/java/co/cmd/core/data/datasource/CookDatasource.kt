package co.cmd.core.data.datasource

import co.cmd.core.domain.ID
import co.cmd.core.domain.Recipe
import co.cmd.core.domain.RecipeDetail

interface CookDatasource {
    suspend fun getRecipes(actualPage: Int, itemsPerPage: Int):Result<List<Recipe>>

    suspend fun getRecipeDetail(recipeID: ID): Result<RecipeDetail>
}