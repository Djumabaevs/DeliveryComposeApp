package com.example.hellocompose.data.login.remoteDS

abstract class BaseRepository(private val api: BaseApi) : SafeApiCall {

    suspend fun logout() = safeApiCall {
    }
}