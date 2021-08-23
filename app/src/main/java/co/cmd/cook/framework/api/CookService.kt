package co.cmd.cook.framework.api

import co.cmd.cook.framework.dto.RecipesResponse
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * The query "format" could be inject with an interceptor to avoid boiler plate code.
 */
interface CookService {

    @POST("/rest/server.api?method=recipes.search&format=json")
    suspend fun getRecipes(
        @Query("page_number") actualPage: Int,
        @Query("max_results") perPage: Int,
    ): RecipesResponse
}