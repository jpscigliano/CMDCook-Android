package co.cmd.cook.di

import co.cmd.cook.BuildConfig
import co.cmd.cook.framework.api.AuthRequestInterceptor
import com.google.gson.GsonBuilder
import okhttp3.Cache
import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

const val TIMEOUT = 10L
const val CACHE_SIZE = 50L * 1024L * 1024L // 50MB
const val CACHE_PATH_NAME = "http-cache"
const val AUTH_QUALIFIER = "auth-qualifier"
const val PLATFORM_QUALIFIER = "platform-qualifier"
const val TOKENIZED_OKHTTP_CLIENT = "tokenized-okhttp-client"
const val TOKEN_INTERCEPTOR = "token-request-interceptor"

val apiModule = module {

    //Gson
    single { GsonBuilder().create() }

    // OkHttp Builder
    single<OkHttpClient.Builder> {
        val cache = Cache(File(androidApplication().cacheDir, CACHE_PATH_NAME), CACHE_SIZE)
        OkHttpClient.Builder().connectTimeout(TIMEOUT, TimeUnit.SECONDS).cache(cache)
    }

    // OkHttpClient. Interceptors can be inject here
    single<OkHttpClient> {
        val okHttpClientBuilder: OkHttpClient.Builder = get()
        okHttpClientBuilder.build()
    }
    // OkHttpClient. That injects an Interceptor with a Token
    single<OkHttpClient>(named(TOKENIZED_OKHTTP_CLIENT)) {
        val okHttpClientBuilder: OkHttpClient.Builder = get()
        okHttpClientBuilder.addInterceptor(get(named(TOKEN_INTERCEPTOR))).build()
    }

    // Retrofit with OAuth url as base url.
    single<Retrofit>(named(AUTH_QUALIFIER)) {
        Retrofit.Builder()
            .client(get())
            .baseUrl(BuildConfig.AUTH_HOST)
            .addConverterFactory(GsonConverterFactory.create(get()))
            .build()
    }
    // Retrofit with main url. Calls  made to platform url should add a Token
    single<Retrofit>(named(PLATFORM_QUALIFIER)) {
        Retrofit.Builder()
            .client(get(named(TOKENIZED_OKHTTP_CLIENT)))
            .baseUrl(BuildConfig.PLATFORM_HOST)
            .addConverterFactory(GsonConverterFactory.create(get()))
            .build()
    }

    //Request interceptor that adds Token to the Header
    single<Interceptor>(named(TOKEN_INTERCEPTOR)){ AuthRequestInterceptor(get()) }

}