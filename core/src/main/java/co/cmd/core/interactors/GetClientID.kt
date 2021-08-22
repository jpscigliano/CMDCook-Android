package co.cmd.core.interactors

import co.cmd.core.data.repository.AuthRepository
import co.cmd.core.domain.ClientID


class GetClientID(private val repository: AuthRepository) {

    operator fun invoke(): ClientID = repository.getClientID()
}