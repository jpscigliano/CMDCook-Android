package co.cmd.cook.framework.dto

import com.google.gson.annotations.SerializedName

class RecipeDetailApiResponse(
    @SerializedName("recipe")
    val recipe: RecipeDetailResponse
)
