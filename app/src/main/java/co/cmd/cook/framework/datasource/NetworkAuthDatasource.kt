package co.cmd.cook.framework.datasource

import android.util.Base64
import co.cmd.cook.framework.Error
import co.cmd.cook.framework.api.AuthService
import co.cmd.cook.framework.safeApiCall
import co.cmd.core.data.datasource.AuthDatasource
import co.cmd.core.domain.ClientID
import co.cmd.core.domain.ClientSecretKey
import co.cmd.core.domain.Token

/**
 * Using SOLID principles. I = interface segregation. Should we add getToken/saveToken with fetchToken ?
 * May be not, should be in different Datasources.
 */
class NetworkAuthDatasource(private val authService: AuthService) : AuthDatasource {

    override suspend fun fetchToken(clientID: ClientID, clientSecretKey: ClientSecretKey): Result<Token> =
        safeApiCall {
            //TODO use Authenticator  from OkHttp
            val auth = "Basic " + Base64.encodeToString(
                ("${clientID.value}:${clientSecretKey.value}").toByteArray(Charsets.UTF_8),
                Base64.DEFAULT
            ).trim()
            Token(authService.fetchToken(auth).token)
        }

    override suspend fun getToken(): Result<Token> =
        Result.failure(Error.Api("Get token from network not implemented"))

    override suspend fun saveToken(token: Token) {
        //TODO should the Token be saved for this user on the servers? This is the place
    }
}