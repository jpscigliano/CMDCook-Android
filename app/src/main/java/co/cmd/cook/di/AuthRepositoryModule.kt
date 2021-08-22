package co.cmd.cook.di

import co.cmd.cook.framework.api.AuthService
import co.cmd.cook.framework.datasource.LocalAuthDatasource
import co.cmd.cook.framework.datasource.LocalConfigDatasource
import co.cmd.cook.framework.datasource.NetworkAuthDatasource
import co.cmd.core.data.datasource.AuthDatasource
import co.cmd.core.data.datasource.ConfigDatasource
import co.cmd.core.data.repository.AuthRepository
import co.cmd.core.interactors.FetchToken
import co.cmd.core.interactors.GetClientID
import co.cmd.core.interactors.GetClientSecret
import co.cmd.core.interactors.GetToken
import co.cmd.core.interactors.SaveToken
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

const val NETWORK = "network"
const val LOCAL = "local"

val authRepositoryModule = module {
    // Repository
    single { AuthRepository(get(named(NETWORK)), get(named(LOCAL)), get()) }

    //Datasource
    single<ConfigDatasource> { LocalConfigDatasource() }
    single<AuthDatasource>(named(NETWORK)) { NetworkAuthDatasource(get()) }
    single<AuthDatasource>(named(LOCAL)) { LocalAuthDatasource(get()) }

    //API
    single { get<Retrofit>(named(AUTH_QUALIFIER)).create(AuthService::class.java) }

    //User of Case
    factory { GetClientSecret(get()) }
    factory { GetClientID(get()) }
    factory { FetchToken(get(), get(), get(), get()) }
    factory { GetToken(get()) }
    factory { SaveToken(get()) }

}