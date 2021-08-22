package co.cmd.cook.framework.api

import co.cmd.core.interactors.GetToken
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Interceptor.Chain
import okhttp3.Response

class AuthRequestInterceptor(private val getToken: GetToken) : Interceptor {

    override fun intercept(chain: Chain): Response {
        val mRequest = chain.request().newBuilder()
            .header("Content-Type", "application/json")
        runBlocking {
            getToken().getOrNull()?.let { token ->
                mRequest.addHeader("Authorization", "Bearer ${token.value}")
            }
        }
        return chain.proceed(mRequest.build())
    }
}