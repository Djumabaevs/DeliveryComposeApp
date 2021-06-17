package com.example.hellocompose.data.login.remoteDS

import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class TokenAuthenticator : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        val updatedToken = getUpdatedToken()
        return response.request.newBuilder()
            .header("Authorization", updatedToken)
            .build()
    }

    private fun getUpdatedToken() {
   
    }



}
