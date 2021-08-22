package co.cmd.cook.framework.datasource

import co.cmd.cook.BuildConfig
import co.cmd.core.data.datasource.ConfigDatasource
import co.cmd.core.domain.ClientID
import co.cmd.core.domain.ClientSecretKey

class LocalConfigDatasource() : ConfigDatasource {

    override fun getClientID(): ClientID = ClientID(BuildConfig.CLIENT_ID)

    override fun getClientSecret(): ClientSecretKey = ClientSecretKey(BuildConfig.CLIENT_SECRET_KEY)
}