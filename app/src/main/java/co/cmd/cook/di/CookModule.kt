package co.cmd.cook.di

import co.cmd.cook.framework.api.CookService
import co.cmd.cook.framework.datasource.NetworkCookDatasource
import co.cmd.core.data.datasource.CookDatasource
import co.cmd.core.data.repository.CookRepository
import co.cmd.core.interactors.GetRecipes
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val cookModule = module {
    // Repository
    single { CookRepository(get()) }

    //Datasource
    single<CookDatasource> { NetworkCookDatasource(get()) }

    //API
    single { get<Retrofit>(named(PLATFORM_QUALIFIER)).create(CookService::class.java) }

    //User of Case
    factory { GetRecipes(get()) }

}