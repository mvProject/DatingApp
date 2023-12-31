/*
 * Create by Medvediev Viktor
 * last update: 14.06.23, 17:19
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.data.model

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
import com.mvproject.datingapp.utils.LONG_ZERO
import com.mvproject.datingapp.utils.STRING_EMPTY

data class User(
    val name: String = STRING_EMPTY,
    val birthdate: Long = LONG_ZERO,
    val email: String = STRING_EMPTY,
    val password: String = STRING_EMPTY,
    val interest: ProfileInterest = ProfileInterest.INTEREST_DATE,
    val gender: ProfileGender = ProfileGender.MALE,
    val uid: String = STRING_EMPTY,
    val location: UserLocation = UserLocation(),
    val profilePictureUrl: String = STRING_EMPTY,
    val photos: List<String> = emptyList(),
    val profileAbout: String = STRING_EMPTY,
    val profileOrientation: ProfileOrientation = ProfileOrientation.ORIENTATION_HETERO,
    val profileMarital: ProfileMarital = ProfileMarital.MARITAL_COMPLICATED,
    val profileChildren: ProfileChildren = ProfileChildren.CHILDREN_NO_WANT,
    val profileHeight: UserHeight = UserHeight(),
    val profileZodiac: ProfileZodiac = ProfileZodiac.ZODIAC_LEO,
    val profileAlcohol: ProfileAlcohol = ProfileAlcohol.ALCOHOL_OFTEN,
    val profileSmoke: ProfileSmoke = ProfileSmoke.SMOKE_SOMETIMES,
    val profilePsyOrientation: ProfilePsyOrientation = ProfilePsyOrientation.PSY_ORIENTATION_BETWEEN,
    val profileReligion: ProfileReligion = ProfileReligion.RELIGION_CATHOLICISM,
    val profileLanguages: List<ProfileLanguage> = emptyList(),
    val profilePets: List<ProfilePets> = emptyList(),
    val profileWork: UserWork = UserWork(),
)