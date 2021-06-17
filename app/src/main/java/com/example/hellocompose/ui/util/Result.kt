package com.example.hellocompose.ui.util

import okhttp3.ResponseBody

sealed class Result<out R> {

    data class Success< T>(val data: T) : Result<T>()
//    data class Error(val exception: Exception) : Result<Nothing>()

    data class Failure(
        val isNetworkError: Boolean,
        val errorCode: Int?,
        val errorBody: ResponseBody?
    ) : Result<Nothing>()

    object Loading : Result<Nothing>()




  /*  override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
            Loading -> "Loading"
        }
    }*/
}

/**
 * `true` if [Result] is of type [Success] & holds non-null [Success.data].
 */
val Result<*>.succeeded
    get() = this is Result.Success && data != null