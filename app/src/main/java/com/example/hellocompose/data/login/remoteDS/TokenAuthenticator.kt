package com.example.hellocompose.data.login.remoteDS

import android.content.Context
import com.example.hellocompose.data.login.localDataSource.LocalDataSource
import com.example.hellocompose.data.login.model.Token
import com.example.hellocompose.data.login.model.TokenRequest
import com.example.hellocompose.data.util.UserPreferencesSerializer
import com.example.hellocompose.ui.util.Result
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

class TokenAuthenticator (
    context: Context,
    private val tokenApi: ApiService
) : Authenticator {

    private val appContext = context.applicationContext
    private val userPreferences =

    override fun authenticate(route: Route?, response: Response): Request? {
        return runBlocking {
            when (val tokenResponse = getUpdatedToken()) {
                is Result.Success<String> -> {
                    userPreferences.saveAccessTokens(
                        tokenResponse.value.access_token!!,
                        tokenResponse.value.refresh_token!!
                    )
                    response.request.newBuilder()
                        .header("Authorization", "Bearer ${tokenResponse.value.access_token}")
                        .build()
                }
                else -> null
            }
        }
    }

    private suspend fun getUpdatedToken(): Resource<TokenResponse> {
        val refreshToken = userPreferences.refreshToken.first()
        return safeApiCall { tokenApi.refreshAccessToken(refreshToken) }
    }



}


