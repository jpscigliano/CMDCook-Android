package co.cmd.core.interactors

import co.cmd.core.data.repository.CookRepository
import co.cmd.core.domain.ID
import co.cmd.core.domain.Recipe
import co.cmd.core.domain.RecipeDetail

class GetRecipeDetail(private val cookRepository: CookRepository) {

    suspend operator fun invoke(recipeID: ID): Result<RecipeDetail> = cookRepository.getRecipeDetail(recipeID)
}