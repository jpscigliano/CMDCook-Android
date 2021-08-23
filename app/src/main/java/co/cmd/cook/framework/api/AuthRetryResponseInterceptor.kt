package co.cmd.cook.framework.api

import co.cmd.cook.framework.dto.ErrorApiResponse
import co.cmd.core.interactors.FetchToken
import com.google.gson.Gson
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Interceptor.Chain
import okhttp3.Response
import okhttp3.ResponseBody

/**
 *
 * This interceptor is in charge of reading the responses and check if the token is invalid.
 */
class AuthRetryResponseInterceptor(
    private val gson: Gson,
    private val fetchToken: FetchToken

) : Interceptor {

    override fun intercept(chain: Chain): Response {
        //Proceed with request
        val response = chain.proceed(chain.request())
        val bodyString = response.body()?.string() ?: ""
        val newResponse =
            response.newBuilder().body(ResponseBody.create(response.body()?.contentType(), bodyString)).build();

        return if (newResponse.isSuccessful) {
            try {
                when (gson.fromJson(bodyString, ErrorApiResponse::class.java).error.code) {
                    2, 9, 13 -> {
                        //The token is OUTdated so we need to fetch the Token again.
                        runBlocking {
                            val token=fetchToken().getOrNull()?.value ?: ""
                            chain.proceed(
                                chain.request().newBuilder().addHeader("Authorization", "Bearer $token").build()
                            )
                        }
                    }
                    else -> newResponse
                }
            } catch (e: Exception) {
                newResponse
            }
        } else {
            newResponse
        }
    }
}