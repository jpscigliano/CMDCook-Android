package co.cmd.core.interactors

import co.cmd.core.data.repository.AuthRepository
import co.cmd.core.domain.Token

class FetchToken(
    private val repository: AuthRepository,
    private val getClientID: GetClientID,
    private val getClientSecret: GetClientSecret
) {

    suspend operator fun invoke(): Result<Token> = repository.fetchToken(getClientID(), getClientSecret())
}