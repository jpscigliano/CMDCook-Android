package co.cmd.cook.framework.datasource

import android.util.Base64
import co.cmd.cook.framework.api.AuthService
import co.cmd.cook.framework.safeApiCall
import co.cmd.core.data.datasource.AuthDatasource
import co.cmd.core.domain.ClientID
import co.cmd.core.domain.ClientSecretKey
import co.cmd.core.domain.Token

class NetworkAuthDatasource(private val authService: AuthService) : AuthDatasource {

    override suspend fun fetchToken(clientID: ClientID, clientSecretKey: ClientSecretKey): Result<Token> = safeApiCall {
        val auth = "Basic " + Base64.encodeToString(
            ("${clientID.value}:${clientSecretKey.value}").toByteArray(Charsets.UTF_8),
            Base64.DEFAULT
        ).trim()
        Token(authService.fetchToken(auth).token)
    }
}