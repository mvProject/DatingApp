/*
 * Create by Medvediev Viktor
 * last update: 21.06.23, 16:43
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.mvproject.datingapp.data.model.User
import com.mvproject.datingapp.utils.LONG_ZERO
import com.mvproject.datingapp.utils.STRING_EMPTY
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject

class PreferenceRepository @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {

    suspend fun setUserLoggedState(state: Boolean) {
        dataStore.edit { settings ->
            settings[IS_USER_LOGGED] = state
        }
    }

    /*    fun getUserLoggedStateFlow() = dataStore.data.map { preferences ->
            preferences[IS_USER_LOGGED] ?: false
        }.distinctUntilChanged()*/

    suspend fun getUserLoggedState() = dataStore.data.map { preferences ->
        preferences[IS_USER_LOGGED] ?: false
    }.first()


    suspend fun saveUser(user: User) {
        dataStore.edit { settings ->
            settings[USER_EMAIL] = user.email
            settings[USER_PASS] = user.password
            settings[USER_GENDER] = user.gender
            settings[USER_NAME] = user.name
            settings[USER_DATE] = user.birthdate
            settings[USER_LOCATION] = user.location
            settings[USER_INTEREST] = user.interest
            settings[USER_PROFILE_PHOTO] = user.profilePictureUrl
            settings[USER_PHOTOS] = user.photos.joinToString(",")
        }
        Timber.w("testing user is saved")
    }

    suspend fun getUser() = dataStore.data.map { preferences ->
        User(
            email = preferences[USER_EMAIL] ?: STRING_EMPTY,
            password = preferences[USER_PASS] ?: STRING_EMPTY,
            gender = preferences[USER_GENDER] ?: STRING_EMPTY,
            name = preferences[USER_NAME] ?: STRING_EMPTY,
            location = preferences[USER_LOCATION] ?: STRING_EMPTY,
            birthdate = preferences[USER_DATE] ?: LONG_ZERO,
            interest = preferences[USER_INTEREST] ?: STRING_EMPTY,
            profilePictureUrl = preferences[USER_PROFILE_PHOTO] ?: STRING_EMPTY,
            photos = preferences[USER_PHOTOS]?.split(",")?.toList().orEmpty()
        )
    }.first()

    private companion object {
        val IS_USER_LOGGED = booleanPreferencesKey("isUserLogged")
        val USER_EMAIL = stringPreferencesKey("userEmail")
        val USER_PASS = stringPreferencesKey("userPass")
        val USER_GENDER = stringPreferencesKey("userGender")
        val USER_NAME = stringPreferencesKey("userName")
        val USER_DATE = longPreferencesKey("userDate")
        val USER_LOCATION = stringPreferencesKey("userLocation")
        val USER_INTEREST = stringPreferencesKey("userInterest")
        val USER_PROFILE_PHOTO = stringPreferencesKey("userProfilePhoto")
        val USER_PHOTOS = stringPreferencesKey("userPhotos")
    }
}
