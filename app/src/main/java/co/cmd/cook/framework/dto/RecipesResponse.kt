package co.cmd.cook.framework.dto

import com.google.gson.annotations.SerializedName

open class RecipesResponse(
    @SerializedName("recipes")
    val pagingResponse: PagingRecipeResponse?,
)
