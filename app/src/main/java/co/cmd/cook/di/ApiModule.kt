package co.cmd.cook.di

import co.cmd.cook.BuildConfig
import com.google.gson.GsonBuilder
import okhttp3.Cache
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

    // Retrofit with OAuth url as base url.
    single<Retrofit>(named(AUTH_QUALIFIER)) {
        Retrofit.Builder()
            .client(get())
            .baseUrl(BuildConfig.AUTH_HOST)
            .addConverterFactory(GsonConverterFactory.create(get()))
            .build()
    }
    // Retrofit with main url.
    single<Retrofit>(named(PLATFORM_QUALIFIER)) {
        Retrofit.Builder()
            .client(get())
            .baseUrl(BuildConfig.PLATFORM_HOST)
            .addConverterFactory(GsonConverterFactory.create(get()))
            .build()
    }

}