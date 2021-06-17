package com.example.hellocompose.data.login.remoteDS

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.hellocompose.data.login.remoteDS.UserPreferences.PreferencesKeys.ACCESS_TOKEN
import com.example.hellocompose.data.login.remoteDS.UserPreferences.PreferencesKeys.REFRESH_TOKEN
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserPreferences(val context: Context, USER_PREFERENCES_NAME: String) {

    private val Context.dataStore by preferencesDataStore(
        name = USER_PREFERENCES_NAME
    )

    val accessToken: Flow<String?>
        get() = context.dataStore.data.map { preferences ->
            ACCESS_TOKEN
        }

    val refreshToken: Flow<String?>
        get() = context.dataStore.data.map { preferences ->
            REFRESH_TOKEN
        }

    suspend fun saveAccessTokens(accessToken: String, refreshToken: String) {
        context.dataStore.edit { preferences ->
            ACCESS_TOKEN = accessToken
            REFRESH_TOKEN = refreshToken
        }
    }

    suspend fun clear() {
        context.dataStore.edit { preferences ->
            preferences.clear()
        }
    }

     object PreferencesKeys {
        var ACCESS_TOKEN = "key_access_token"
        var REFRESH_TOKEN = "key_refresh_token"
    }


}