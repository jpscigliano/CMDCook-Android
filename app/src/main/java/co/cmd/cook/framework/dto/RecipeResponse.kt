package co.cmd.cook.framework.dto

import com.google.gson.annotations.SerializedName

class RecipeResponse(
    @SerializedName("recipe_id")
    val id: String?,
    @SerializedName("recipe_name")
    val name: String?,
    @SerializedName("recipe_description")
    val description: String?,
    @SerializedName("recipe_image")
    val imageUrl: String?,
    @SerializedName("recipe_nutrition")
    val nutrition: NutritionResponse?,

)
