package co.cmd.cook.framework.datasource

import co.cmd.cook.framework.api.CookService
import co.cmd.cook.framework.safeApiCall
import co.cmd.cook.mappers.RecipeDetailResponseToRecipeDetailMapper
import co.cmd.cook.mappers.RecipeResponseToRecipeMapper
import co.cmd.core.data.datasource.CookDatasource
import co.cmd.core.domain.ID
import co.cmd.core.domain.Recipe
import co.cmd.core.domain.RecipeDetail

/**
 * Following SOLID principles, D= Dependency inversion.
 * RecipeResponseToRecipeMapper should be inject, but as a Mapper abstraction. For now we create it here.
 */
class NetworkCookDatasource(private val cookService: CookService) : CookDatasource {

    override suspend fun getRecipes(actualPage: Int, itemsPerPage: Int): Result<List<Recipe>> = safeApiCall {
        val mapper = RecipeResponseToRecipeMapper()
        cookService.getRecipes(actualPage,itemsPerPage).pagingResponse?.recipes?.map {
            mapper.map(it)
        } ?: listOf()
    }

    override suspend fun getRecipeDetail(recipeID: ID): Result<RecipeDetail> = safeApiCall {
        val response = cookService.getRecipeDetail(recipeID.value)
        val mapper = RecipeDetailResponseToRecipeDetailMapper()
        mapper.map(response.recipe)
    }
}
