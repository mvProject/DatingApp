package com.mvproject.datingapp.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PreferenceRepository @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {

    suspend fun setUserLoggedState(state: Boolean) {
        dataStore.edit { settings ->
            settings[IS_USER_LOGGED] = state
        }
    }

    fun getUserLoggedState() = dataStore.data.map { preferences ->
        preferences[IS_USER_LOGGED] ?: false
    }.distinctUntilChanged()

    private companion object {
        val IS_USER_LOGGED = booleanPreferencesKey("isUserLogged")
    }
}
