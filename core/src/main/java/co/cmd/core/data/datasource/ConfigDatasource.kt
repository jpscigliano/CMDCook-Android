package co.cmd.core.data.datasource

import co.cmd.core.domain.ClientID
import co.cmd.core.domain.ClientSecretKey

interface ConfigDatasource {

    fun getClientID(): ClientID
    fun getClientSecret(): ClientSecretKey
}