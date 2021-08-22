package co.cmd.cook.framework.datasource

import co.cmd.cook.framework.api.CookService
import co.cmd.cook.framework.safeApiCall
import co.cmd.cook.mappers.RecipeResponseToRecipeMapper
import co.cmd.core.data.datasource.CookDatasource
import co.cmd.core.domain.Recipe

/**
 * Following SOLID principles, D= Dependency inversion.
 * RecipeResponseToRecipeMapper should be inject, but as a Mapper abstraction. For now we create it here.
 */
class NetworkCookDatasource(private val cookService: CookService) : CookDatasource {

    override suspend fun getRecipes(amountPerPage: Int, page: Int): Result<List<Recipe>> = safeApiCall {
        val mapper = RecipeResponseToRecipeMapper()
        cookService.getRecipes(amountPerPage, page).pagingResponse?.recipes?.map {
            mapper.map(it)
        } ?: listOf()
    }
}
