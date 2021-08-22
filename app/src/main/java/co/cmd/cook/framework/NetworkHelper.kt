package co.cmd.cook.framework

import com.google.gson.GsonBuilder
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

suspend fun <T> safeApiCall(dispatcher: CoroutineDispatcher = Dispatchers.IO, apiCall: suspend () -> T): Result<T> {
    return withContext(dispatcher) {
        try {
            Result.success(apiCall())
        } catch (throwable: Throwable) {
            when (throwable) {
                is HttpException -> {
                    val gson = GsonBuilder().create()
                    try {
                        //TODO Try to parse the error response from the API
                        Result.failure(Error.Api(""))
                    } catch (e: Exception) {
                        Result.failure(e)
                    }
                }
                else -> Result.failure(Error.Unknown())
            }
        }
    }
}

sealed class Error(thMessage: String) : Throwable(thMessage) {
    class Api(message: String) : Error(message)
    class Unknown : Error("Unknown error")
}
