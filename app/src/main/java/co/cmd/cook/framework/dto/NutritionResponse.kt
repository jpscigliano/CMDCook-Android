package co.cmd.cook.framework.dto

import com.google.gson.annotations.SerializedName

class NutritionResponse(
    @SerializedName("calories")
    val calories: String?,
    @SerializedName("carbohydrate")
    val carbs: String?,
    @SerializedName("fat")
    val fat: String?,
    @SerializedName("protein")
    val protein: String?,
)
