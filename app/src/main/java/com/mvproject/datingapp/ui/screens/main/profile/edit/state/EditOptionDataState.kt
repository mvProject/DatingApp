/*
 * Create by Medvediev Viktor
 * last update: 16.06.23, 16:58
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.main.profile.edit.state

import com.mvproject.datingapp.data.enums.ProfileAlcohol
import com.mvproject.datingapp.data.enums.ProfileChildren
import com.mvproject.datingapp.data.enums.ProfileInterest
import com.mvproject.datingapp.data.enums.ProfileLanguage
import com.mvproject.datingapp.data.enums.ProfileMarital
import com.mvproject.datingapp.data.enums.ProfileOrientation
import com.mvproject.datingapp.data.enums.ProfilePets
import com.mvproject.datingapp.data.enums.ProfilePsyOrientation
import com.mvproject.datingapp.data.enums.ProfileReligion
import com.mvproject.datingapp.data.enums.ProfileSmoke
import com.mvproject.datingapp.data.enums.ProfileZodiac
import com.mvproject.datingapp.data.model.UserHeight
import com.mvproject.datingapp.data.model.UserLocation
import com.mvproject.datingapp.data.model.UserWork
import com.mvproject.datingapp.utils.STRING_EMPTY

data class EditOptionDataState(
    val currentOption: EditProfileOption = EditProfileOption.ABOUT,
    val profileInterest: ProfileInterest = ProfileInterest.INTEREST_LOVE,
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
    val profileLanguages: List<ProfileLanguage> = ProfileLanguage.defaultLanguageList,
    val profilePets: List<ProfilePets> = ProfilePets.defaultPetList,
    val profileWork: UserWork = UserWork(),
    val profileLocation: UserLocation = UserLocation(),
    val isComplete: Boolean = false
)
