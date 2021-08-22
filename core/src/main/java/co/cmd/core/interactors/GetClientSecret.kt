package co.cmd.core.interactors

import co.cmd.core.data.repository.AuthRepository
import co.cmd.core.domain.ClientSecretKey

class GetClientSecret(private val repository: AuthRepository) {

    operator fun invoke(): ClientSecretKey = repository.getClientSecretKet()
}