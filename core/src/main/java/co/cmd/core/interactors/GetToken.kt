package co.cmd.core.interactors

import co.cmd.core.data.repository.AuthRepository
import co.cmd.core.domain.Token

class GetToken(
    private val repository: AuthRepository,
) {

    suspend operator fun invoke(): Result<Token> = repository.getToken()
}