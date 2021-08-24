package co.cmd.cook.framework.dto

import com.google.gson.annotations.SerializedName

class ServingsResponse(
    @SerializedName("calcium")
    val calcium: String?,
    @SerializedName("calories")
    val calories: String?,
    @SerializedName("carbohydrate")
    val carbohydrate: String?,
    @SerializedName("cholesterol")
    val cholesterol: String?,
    @SerializedName("fat")
    val fat: String?,
    @SerializedName("fiber")
    val fiber: String?,
    @SerializedName("iron")
    val iron: String?,
    @SerializedName("potassium")
    val potassium: String?,
    @SerializedName("sodium")
    val sodium: String?,
    @SerializedName("sugar")
    val sugar: String?,
)
