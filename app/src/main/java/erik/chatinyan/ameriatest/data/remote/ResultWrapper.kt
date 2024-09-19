package erik.chatinyan.ameriatest.data.remote

import com.google.gson.JsonElement

sealed class ResultWrapper<out T> {
    data class Success<out T>(val value: T): ResultWrapper<T>()
    data class GenericError(val code: Int? = null, val error: String? = null): ResultWrapper<Nothing>()
    object NetworkException: ResultWrapper<Nothing>()
}