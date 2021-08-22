package co.cmd.core.interactors

import co.cmd.core.data.repository.AuthRepository
import co.cmd.core.domain.Token

class FetchToken(
    private val repository: AuthRepository,
    private val getClientID: GetClientID,
    private val getClientSecret: GetClientSecret,
    private val saveToken: SaveToken
) {

    suspend operator fun invoke(): Result<Token> {
        val tokenResult = repository.fetchToken(getClientID(), getClientSecret())
        tokenResult.getOrNull()?.let { token ->
            saveToken(token)
        }
        return tokenResult
    }
}