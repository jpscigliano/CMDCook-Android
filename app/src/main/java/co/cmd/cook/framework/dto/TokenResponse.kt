package co.cmd.cook.framework.dto

import co.cmd.core.domain.Token
import com.google.gson.annotations.SerializedName

class TokenResponse(
    @SerializedName("access_token")
    val token: String,
    @SerializedName("token_type")
    val type: String,
    @SerializedName("expires_in")
    val expiredIn: Int
)