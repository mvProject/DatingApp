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
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.mvproject.datingapp.data.enums.filter.FilterAlcohol
import com.mvproject.datingapp.data.enums.filter.FilterCharacter
import com.mvproject.datingapp.data.enums.filter.FilterChildren
import com.mvproject.datingapp.data.enums.filter.FilterGender
import com.mvproject.datingapp.data.enums.filter.FilterInterest
import com.mvproject.datingapp.data.enums.filter.FilterLanguage
import com.mvproject.datingapp.data.enums.filter.FilterMarital
import com.mvproject.datingapp.data.enums.filter.FilterOrientation
import com.mvproject.datingapp.data.enums.filter.FilterPets
import com.mvproject.datingapp.data.enums.filter.FilterReligion
import com.mvproject.datingapp.data.enums.filter.FilterSmoke
import com.mvproject.datingapp.data.enums.filter.FilterZodiac
import com.mvproject.datingapp.data.enums.profile.ProfileAlcohol
import com.mvproject.datingapp.data.enums.profile.ProfileChildren
import com.mvproject.datingapp.data.enums.profile.ProfileGender
import com.mvproject.datingapp.data.enums.profile.ProfileInterest
import com.mvproject.datingapp.data.enums.profile.ProfileLanguage
import com.mvproject.datingapp.data.enums.profile.ProfileMarital
import com.mvproject.datingapp.data.enums.profile.ProfileOrientation
import com.mvproject.datingapp.data.enums.profile.ProfilePets
import com.mvproject.datingapp.data.enums.profile.ProfilePsyOrientation
import com.mvproject.datingapp.data.enums.profile.ProfileReligion
import com.mvproject.datingapp.data.enums.profile.ProfileSmoke
import com.mvproject.datingapp.data.enums.profile.ProfileZodiac
import com.mvproject.datingapp.data.model.FilterData
import com.mvproject.datingapp.data.model.User
import com.mvproject.datingapp.data.model.UserActivation
import com.mvproject.datingapp.data.model.UserHeight
import com.mvproject.datingapp.data.model.UserLocation
import com.mvproject.datingapp.data.model.UserWork
import com.mvproject.datingapp.utils.DEFAULT_FILTER_AGE_MAX
import com.mvproject.datingapp.utils.DEFAULT_FILTER_AGE_MIN
import com.mvproject.datingapp.utils.DEFAULT_FILTER_DISTANCE
import com.mvproject.datingapp.utils.DEFAULT_FILTER_HEIGHT_MAX
import com.mvproject.datingapp.utils.DEFAULT_FILTER_HEIGHT_MIN
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
            settings[USER_LOCATION] = user.location.toString()
            settings[USER_INTEREST] = user.interest.name
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
            gender = preferences[USER_GENDER] ?: ProfileGender.MALE.name,
            name = preferences[USER_NAME] ?: STRING_EMPTY,
            location = UserLocation.fromStringOrDefault(preferences[USER_LOCATION]),
            birthdate = preferences[USER_DATE] ?: LONG_ZERO,
            interest = ProfileInterest.fromStringOrDefault(preferences[USER_INTEREST]),
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

    suspend fun getUserFlow() = dataStore.data.map { preferences ->
        User(
            email = preferences[USER_EMAIL] ?: STRING_EMPTY,
            password = preferences[USER_PASS] ?: STRING_EMPTY,
            gender = preferences[USER_GENDER] ?: STRING_EMPTY,
            name = preferences[USER_NAME] ?: STRING_EMPTY,
            location = UserLocation.fromStringOrDefault(preferences[USER_LOCATION]),
            birthdate = preferences[USER_DATE] ?: LONG_ZERO,
            interest = ProfileInterest.fromStringOrDefault(preferences[USER_INTEREST]),
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
    }

    suspend fun saveDatingFilters(data: FilterData) {
        dataStore.edit { settings ->
            settings[FILTER_DISTANCE] = data.distance
            settings[FILTER_GENDER] = data.filterGender.name
            settings[FILTER_INTEREST] = data.filterInterests.joinToString(STRING_SEPARATOR)
            settings[FILTER_ORIENTATION] = data.filterOrientation.name
            settings[FILTER_MARITAL] = data.filterMaritals.joinToString(STRING_SEPARATOR)
            settings[FILTER_CHILDREN] = data.filterChildrens.joinToString(STRING_SEPARATOR)
            settings[FILTER_START_HEIGHT] = data.startHeight
            settings[FILTER_END_HEIGHT] = data.endHeight
            settings[FILTER_START_AGE] = data.startAge
            settings[FILTER_END_AGE] = data.endAge
            settings[FILTER_IS_HEIGHT_SET] = data.isHeightSet
            settings[FILTER_ZODIAC] = data.filterZodiacs.joinToString(STRING_SEPARATOR)
            settings[FILTER_ALCOHOL] = data.filterAlcohols.joinToString(STRING_SEPARATOR)
            settings[FILTER_SMOKE] = data.filterSmoke.name
            settings[FILTER_CHARACTER] = data.filterCharacter.joinToString(STRING_SEPARATOR)
            settings[FILTER_RELIGION] = data.filterReligions.joinToString(STRING_SEPARATOR)
            settings[FILTER_LANGUAGE] = data.filterLanguages.joinToString(STRING_SEPARATOR)
            settings[FILTER_PET] = data.filterPets.joinToString(STRING_SEPARATOR)
        }
    }

    suspend fun getDatingFilters() = dataStore.data.map { preferences ->
        FilterData(
            distance = preferences[FILTER_DISTANCE] ?: DEFAULT_FILTER_DISTANCE,
            filterGender = FilterGender.fromStringOrDefault(preferences[FILTER_GENDER]),
            startHeight = preferences[FILTER_START_HEIGHT] ?: DEFAULT_FILTER_HEIGHT_MIN,
            endHeight = preferences[FILTER_END_HEIGHT] ?: DEFAULT_FILTER_HEIGHT_MAX,
            startAge = preferences[FILTER_START_AGE] ?: DEFAULT_FILTER_AGE_MIN,
            endAge = preferences[FILTER_END_AGE] ?: DEFAULT_FILTER_AGE_MAX,
            isHeightSet = preferences[FILTER_IS_HEIGHT_SET] ?: false,
            filterInterests = FilterInterest.fromStringOrDefault(preferences[FILTER_INTEREST]),
            filterOrientation = FilterOrientation.fromStringOrDefault(preferences[FILTER_ORIENTATION]),
            filterMaritals = FilterMarital.fromStringOrDefault(preferences[FILTER_MARITAL]),
            filterChildrens = FilterChildren.fromStringOrDefault(preferences[FILTER_CHILDREN]),
            filterZodiacs = FilterZodiac.fromStringOrDefault(preferences[FILTER_ZODIAC]),
            filterAlcohols = FilterAlcohol.fromStringOrDefault(preferences[FILTER_ALCOHOL]),
            filterSmoke = FilterSmoke.fromStringOrDefault(preferences[FILTER_SMOKE]),
            filterCharacter = FilterCharacter.fromStringOrDefault(preferences[FILTER_CHARACTER]),
            filterReligions = FilterReligion.fromStringOrDefault(preferences[FILTER_RELIGION]),
            filterLanguages = FilterLanguage.fromStringOrDefault(preferences[FILTER_LANGUAGE]),
            filterPets = FilterPets.fromStringOrDefault(preferences[FILTER_PET]),
        )
    }

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

        val FILTER_IS_HEIGHT_SET = booleanPreferencesKey("filterIsHeightSet")
        val FILTER_START_AGE = intPreferencesKey("filterStartAge")
        val FILTER_END_AGE = intPreferencesKey("filterEndAge")
        val FILTER_START_HEIGHT = intPreferencesKey("filterStartHeight")
        val FILTER_END_HEIGHT = intPreferencesKey("filterEndHeight")
        val FILTER_DISTANCE = intPreferencesKey("filterDistance")

        val FILTER_GENDER = stringPreferencesKey("filterGender")
        val FILTER_ORIENTATION = stringPreferencesKey("filterOrientation")
        val FILTER_MARITAL = stringPreferencesKey("filterMarital")
        val FILTER_INTEREST = stringPreferencesKey("filterInterest")
        val FILTER_CHILDREN = stringPreferencesKey("filterChildren")
        val FILTER_ZODIAC = stringPreferencesKey("filterZodiac")
        val FILTER_ALCOHOL = stringPreferencesKey("filterAlcohol")
        val FILTER_SMOKE = stringPreferencesKey("filterSmoke")
        val FILTER_CHARACTER = stringPreferencesKey("filterCharacter")
        val FILTER_RELIGION = stringPreferencesKey("filterReligion")
        val FILTER_LANGUAGE = stringPreferencesKey("filterLanguage")
        val FILTER_PET = stringPreferencesKey("filterPet")
    }
}