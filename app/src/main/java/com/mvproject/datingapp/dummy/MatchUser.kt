/*
 * Create by Medvediev Viktor
 * last update: 14.06.23, 17:19
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.dummy

import com.mvproject.datingapp.data.enums.ProfileAlcohol
import com.mvproject.datingapp.data.enums.ProfileChildren
import com.mvproject.datingapp.data.enums.ProfileInterest
import com.mvproject.datingapp.data.enums.ProfileLanguage
import com.mvproject.datingapp.data.enums.ProfileLanguage.Companion.defaultLanguageList
import com.mvproject.datingapp.data.enums.ProfileMarital
import com.mvproject.datingapp.data.enums.ProfileOrientation
import com.mvproject.datingapp.data.enums.ProfilePets
import com.mvproject.datingapp.data.enums.ProfilePets.Companion.defaultPetList
import com.mvproject.datingapp.data.enums.ProfilePsyOrientation
import com.mvproject.datingapp.data.enums.ProfileReligion
import com.mvproject.datingapp.data.enums.ProfileSmoke
import com.mvproject.datingapp.data.enums.ProfileZodiac
import com.mvproject.datingapp.data.model.UserHeight
import com.mvproject.datingapp.data.model.UserWork
import com.mvproject.datingapp.utils.INT_ZERO
import com.mvproject.datingapp.utils.LONG_ZERO
import com.mvproject.datingapp.utils.STRING_EMPTY

data class MatchUser(
    val id: Int = INT_ZERO,
    val name: String = STRING_EMPTY,
    val birthdate: Long = LONG_ZERO,
    val email: String = STRING_EMPTY,
    val password: String = STRING_EMPTY,
    val interest: ProfileInterest = ProfileInterest.INTEREST_DATE,
    val gender: String = STRING_EMPTY,
    val uid: String = STRING_EMPTY,
    val location: String = STRING_EMPTY,
    val profilePictureUrl: Int = INT_ZERO,
    val photos: List<Int> = emptyList(),
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
    val profileLanguages: List<ProfileLanguage> = defaultLanguageList,
    val profilePets: List<ProfilePets> = defaultPetList,
    val profileWork: UserWork = UserWork(),
)