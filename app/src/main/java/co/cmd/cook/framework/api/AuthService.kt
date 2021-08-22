package co.cmd.cook.framework.api

import co.cmd.cook.framework.dto.TokenResponse
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthService {

    @POST("/connect/token")
    fun fetchToken(@Header("Authorization") auth: String): TokenResponse
}