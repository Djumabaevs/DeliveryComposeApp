package com.example.hellocompose.data.login.remoteDS

import com.example.hellocompose.ui.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

interface  SafeApiCall {
    suspend fun <T> safeApiCall(
        apiCall: suspend () -> T
    ): Result<T> {
        return withContext(Dispatchers.IO) {
            try {
                Result.Success(apiCall.invoke())
            } catch (throwable: Throwable) {
                when (throwable) {
                    is HttpException -> {
                        Result.Failure(false, throwable.code(), throwable.response()?.errorBody())
                    }
                    else -> {
                        Result.Failure(true, null, null)
                    }
                }
            }
        }
    }
}