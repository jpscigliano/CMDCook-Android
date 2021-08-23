package co.cmd.core.interactors

import co.cmd.core.data.repository.CookRepository
import co.cmd.core.domain.Recipe

class GetRecipes(private val cookRepository: CookRepository) {

    suspend operator fun invoke(actualPage: Int, itemsPerPage: Int): Result<List<Recipe>> =
        cookRepository.getRecipes(actualPage, itemsPerPage)
}