package co.cmd.cook.framework.dto

import com.google.gson.annotations.SerializedName

class ErrorApiResponse(
    @SerializedName("error")
    val error: ErrorResponse
)

class ErrorResponse(
    @SerializedName("code")
    val code: Int,
    @SerializedName("message")
    val message: String?,
)
