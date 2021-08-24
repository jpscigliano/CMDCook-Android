package co.cmd.cook.framework.dto

import com.google.gson.annotations.SerializedName

class RecipeDetailResponse(
    @SerializedName("recipe_id")
    val id: String?,
    @SerializedName("recipe_name")
    val name: String?,
    @SerializedName("recipe_description")
    val description: String?,
    @SerializedName("recipe_images")
    val recipeImage: RecipeImage?,
    @SerializedName("recipe_nutrition")
    val nutrition: NutritionResponse?,
    @SerializedName("number_of_servings")
    val numberServings: String?,
    @SerializedName("preparation_time_min")
    val prepTimeMin: String?,
    @SerializedName("cooking_time_min")
    val cookTimeMin: String?,
    @SerializedName("rating")
    val rating: String?,
    @SerializedName("serving_sizes")
    val servings: ServingsResponse?,
)

class RecipeImage(
    @SerializedName("recipe_image")
    val imageUrl: String?
)