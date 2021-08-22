package co.cmd.core.interactors

import co.cmd.core.data.repository.AuthRepository
import co.cmd.core.domain.Token

class SaveToken(
    private val repository: AuthRepository,
) {

    suspend operator fun invoke(token: Token): Unit = repository.saveToken(token)
}