package co.cmd.cook.framework.datasource

import co.cmd.cook.framework.Error
import co.cmd.core.data.datasource.AuthDatasource
import co.cmd.core.domain.ClientID
import co.cmd.core.domain.ClientSecretKey
import co.cmd.core.domain.Token

class LocalAuthDatasource() : AuthDatasource {

    override suspend fun fetchToken(clientID: ClientID, clientSecretKey: ClientSecretKey): Result<Token> =
        Result.failure(Error.Api("Generate token locally is not implemented"))

    override suspend fun getToken(): Result<Token> {
        //TODO Implement get Token
        return Result.failure(Error.Api("GET token not implemented yet"))
    }

    override suspend fun saveToken(token: Token) {
    }
}