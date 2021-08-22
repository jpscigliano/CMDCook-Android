package co.cmd.core.data.datasource

import co.cmd.core.domain.ClientID
import co.cmd.core.domain.ClientSecretKey
import co.cmd.core.domain.Token

interface AuthDatasource {

    suspend fun fetchToken(clientID: ClientID, clientSecretKey: ClientSecretKey): Result<Token>
    suspend fun getToken(): Result<Token>
    suspend fun saveToken(token: Token)
}