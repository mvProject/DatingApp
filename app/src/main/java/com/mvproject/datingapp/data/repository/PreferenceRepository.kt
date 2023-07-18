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
import com.mvproject.datingapp.data.enums.ProfileAlcohol
import com.mvproject.datingapp.data.enums.ProfileChildren
import com.mvproject.datingapp.data.enums.ProfileLanguage
import com.mvproject.datingapp.data.enums.ProfileMarital
import com.mvproject.datingapp.data.enums.ProfileOrientation
import com.mvproject.datingapp.data.enums.ProfilePets
import com.mvproject.datingapp.data.enums.ProfilePsyOrientation
import com.mvproject.datingapp.data.enums.ProfileReligion
import com.mvproject.datingapp.data.enums.ProfileSmoke
import com.mvproject.datingapp.data.enums.ProfileZodiac
import com.mvproject.datingapp.data.model.User
import com.mvproject.datingapp.data.model.UserActivation
import com.mvproject.datingapp.data.model.UserHeight
import com.mvproject.datingapp.data.model.UserWork
import com.mvproject.datingapp.utils.LONG_ZERO
import com.mvproject.datingapp.utils.STRING_EMPTY
import com.mvproject.datingapp.utils.STRING_SEPARATOR
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

    suspend fun getUserLoggedState() = dataStore.data.map { preferences ->
        preferences[IS_USER_LOGGED] ?: false
    }.first()

    suspend fun setActivationState(activation: UserActivation) {
        dataStore.edit { settings ->
            settings[ACTIVATION_STATUS] = activation.status
            settings[ACTIVATION_PERIOD] = activation.period

        }
        Timber.w("testing activation is saved")
    }

    suspend fun getActivationState() = dataStore.data.map { preferences ->
        UserActivation(
            status = preferences[ACTIVATION_STATUS] ?: false,
            period = preferences[ACTIVATION_PERIOD] ?: LONG_ZERO
        )
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
            settings[USER_PHOTOS] = user.photos.joinToString(STRING_SEPARATOR)
            settings[USER_ABOUT] = user.profileAbout
            settings[USER_ORIENTATION] = user.profileOrientation.name
            settings[USER_MARITAL] = user.profileMarital.name
            settings[USER_CHILDREN] = user.profileChildren.name
            settings[USER_HEIGHT] = user.profileHeight.toString()
            settings[USER_ZODIAC] = user.profileZodiac.name
            settings[USER_ALCOHOL] = user.profileAlcohol.name
            settings[USER_SMOKE] = user.profileSmoke.name
            settings[USER_PSY_ORIENTATION] = user.profilePsyOrientation.name
            settings[USER_RELIGION] = user.profileReligion.name
            settings[USER_LANGUAGES] = user.profileLanguages.joinToString(STRING_SEPARATOR)
            settings[USER_PETS] = user.profilePets.joinToString(STRING_SEPARATOR)
            settings[USER_WORK] = user.profileWork.toString()
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
            photos = preferences[USER_PHOTOS]?.split(STRING_SEPARATOR)?.toList().orEmpty(),
            profileAbout = preferences[USER_ABOUT] ?: STRING_EMPTY,
            profileOrientation = ProfileOrientation.fromStringOrDefault(preferences[USER_ORIENTATION]),
            profileMarital = ProfileMarital.fromStringOrDefault(preferences[USER_MARITAL]),
            profileChildren = ProfileChildren.fromStringOrDefault(preferences[USER_CHILDREN]),
            profileHeight = UserHeight.fromStringOrDefault(preferences[USER_HEIGHT]),
            profileZodiac = ProfileZodiac.fromStringOrDefault(preferences[USER_ZODIAC]),
            profileAlcohol = ProfileAlcohol.fromStringOrDefault(preferences[USER_ALCOHOL]),
            profileSmoke = ProfileSmoke.fromStringOrDefault(preferences[USER_SMOKE]),
            profilePsyOrientation = ProfilePsyOrientation.fromStringOrDefault(preferences[USER_PSY_ORIENTATION]),
            profileReligion = ProfileReligion.fromStringOrDefault(preferences[USER_RELIGION]),
            profileLanguages = ProfileLanguage.fromStringOrDefault(preferences[USER_LANGUAGES]),
            profilePets = ProfilePets.fromStringOrDefault(preferences[USER_PETS]),
            profileWork = UserWork.fromStringOrDefault(preferences[USER_WORK])
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
        val USER_ABOUT = stringPreferencesKey("userAbout")
        val USER_ORIENTATION = stringPreferencesKey("userOrientation")
        val USER_MARITAL = stringPreferencesKey("userMarital")
        val USER_CHILDREN = stringPreferencesKey("userChildren")
        val USER_HEIGHT = stringPreferencesKey("userHeight")
        val USER_ZODIAC = stringPreferencesKey("userZodiac")
        val USER_ALCOHOL = stringPreferencesKey("userAlcohol")
        val USER_SMOKE = stringPreferencesKey("userSmoke")
        val USER_PSY_ORIENTATION = stringPreferencesKey("userPsyOrientation")
        val USER_RELIGION = stringPreferencesKey("userReligion")
        val USER_LANGUAGES = stringPreferencesKey("userLanguages")
        val USER_PETS = stringPreferencesKey("userPets")
        val USER_WORK = stringPreferencesKey("userWork")

        val ACTIVATION_STATUS = booleanPreferencesKey("activationStatus")
        val ACTIVATION_PERIOD = longPreferencesKey("activationPeriod")
    }
}