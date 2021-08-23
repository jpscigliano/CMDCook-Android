package co.cmd.cook.framework.api

import co.cmd.cook.framework.dto.TokenResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthService {

    @FormUrlEncoded
    @POST("/connect/token")
    suspend fun fetchToken(
        @Header("Authorization") auth: String,
        @Field("scope") scope: String = "basic",
        @Field("grant_type") grantType: String = "client_credentials",
    ): TokenResponse
}