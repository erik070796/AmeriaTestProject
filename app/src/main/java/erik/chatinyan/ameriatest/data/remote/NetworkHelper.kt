package erik.chatinyan.ameriatest.data.remote

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

suspend fun <T> safeApiCall(
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    apiCall: suspend () -> Response<T>
): ResultWrapper<T> {
    return withContext(dispatcher) {
        try {
            val response = apiCall.invoke()
            val body = response.body()
            if (response.isSuccessful && body != null) {
                ResultWrapper.Success(body)
            } else {
                ResultWrapper.GenericError(response.code(), response.message())
            }
        } catch (throwable: Throwable) {
            ResultWrapper.NetworkException
        }
    }
}