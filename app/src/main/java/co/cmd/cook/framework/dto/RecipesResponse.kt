package co.cmd.cook.framework.dto

import com.google.gson.annotations.SerializedName

class RecipesResponse(
    @SerializedName("recipes")
    val pagingResponse: PagingRecipeResponse?,
)
