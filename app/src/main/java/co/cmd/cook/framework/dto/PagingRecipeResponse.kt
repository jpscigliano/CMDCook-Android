package co.cmd.cook.framework.dto

import com.google.gson.annotations.SerializedName

class PagingRecipeResponse(
    @SerializedName("max_results")
    val maxResults: String?,
    @SerializedName("page_number")
    val page: String?,
    @SerializedName("total_results")
    val totalResults: String?,
    @SerializedName("recipe")
    val recipes: List<RecipeResponse>?,
)
