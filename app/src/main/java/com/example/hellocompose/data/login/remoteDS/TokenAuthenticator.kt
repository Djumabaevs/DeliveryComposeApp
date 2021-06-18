package com.example.hellocompose.data.login.remoteDS

import android.content.Context
import com.example.hellocompose.data.login.localDataSource.LocalDataSource
import com.example.hellocompose.data.login.model.Token
import com.example.hellocompose.data.login.model.TokenRequest
import com.example.hellocompose.data.util.UserPreferencesSerializer
import com.example.hellocompose.ui.util.Result
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

class TokenAuthenticator(
    context: Context,
    private val tokenApi: TokenRefreshApi
) : Authenticator, BaseRepository(tokenApi) {

    private val userPreferences = UserPreferences(context, "Bask")

    override fun authenticate(route: Route?, response: Response): Request? {
        return runBlocking {
            when (val tokenResponse = getUpdatedToken()) {
                is Result.Success -> {
                    userPreferences.saveAccessTokens(
                        tokenResponse.data.accessToken,
                        tokenResponse.data.refreshToken
                    )
                    response.request.newBuilder()
                        .header("Authorization", "Bearer ${tokenResponse.data.accessToken}")
                        .build()
                }
                else -> null
            }
        }
    }

    private suspend fun getUpdatedToken(): Result<Token> {
        val refreshedToken = userPreferences.refreshToken.first()
        return tokenApi.refreshAccessToken(refreshedToken).ifSuccessR()

//        return  safeApiCall {tokenApi.refreshAccessToken(refreshedToken)}

    }


}


