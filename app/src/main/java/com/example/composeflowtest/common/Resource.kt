package com.example.composeflowtest.common

sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
    class Loading<T>(data: T? = null) : Resource<T>(data)
}

suspend fun <T : Any> safeApiCall(call: suspend () -> Resource<T>): Resource<T> = try {
    call.invoke()
} catch (e: Exception) {
    Resource.Error(e.message.toString(), null)
}
