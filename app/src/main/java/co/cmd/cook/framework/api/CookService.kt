package co.cmd.cook.framework.api

import co.cmd.cook.framework.dto.RecipeResponse
import co.cmd.cook.framework.dto.RecipesResponse
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * The query "format" could be inject with an interceptor to avoid boiler plate code.
 */
interface CookService {

    @POST("/?method=recipes.search&format=json")
    fun getRecipes(
        @Query("max_results") amount: Int,
        @Query("page_number") page: Int,
    ): RecipesResponse
}