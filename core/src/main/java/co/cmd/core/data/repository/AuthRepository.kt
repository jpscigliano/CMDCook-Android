package co.cmd.core.data.repository

import co.cmd.core.data.datasource.AuthDatasource
import co.cmd.core.data.datasource.ConfigDatasource
import co.cmd.core.domain.ClientID
import co.cmd.core.domain.ClientSecretKey
import co.cmd.core.domain.Token

class AuthRepository(
    private val networkDatasource: AuthDatasource,
    private val localDatasource: AuthDatasource,
    private val configDatasource: ConfigDatasource

) {

    suspend fun fetchToken(clientID: ClientID, clientSecretKey: ClientSecretKey): Result<Token> =
        networkDatasource.fetchToken(clientID, clientSecretKey)

    fun getClientID(): ClientID = configDatasource.getClientID()

    fun getClientSecretKet(): ClientSecretKey = configDatasource.getClientSecret()

    suspend fun getToken(): Result<Token> = localDatasource.getToken()
    suspend fun saveToken(token: Token) = localDatasource.saveToken(token)
}