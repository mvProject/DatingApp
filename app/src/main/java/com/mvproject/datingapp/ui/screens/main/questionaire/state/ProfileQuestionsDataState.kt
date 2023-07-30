/*
 * Create by Medvediev Viktor
 * last update: 16.06.23, 16:58
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.main.questionaire.state

import com.mvproject.datingapp.data.enums.profile.ProfileAlcohol
import com.mvproject.datingapp.data.enums.profile.ProfileChildren
import com.mvproject.datingapp.data.enums.profile.ProfileLanguage
import com.mvproject.datingapp.data.enums.profile.ProfileMarital
import com.mvproject.datingapp.data.enums.profile.ProfileOrientation
import com.mvproject.datingapp.data.enums.profile.ProfilePets
import com.mvproject.datingapp.data.enums.profile.ProfilePsyOrientation
import com.mvproject.datingapp.data.enums.profile.ProfileReligion
import com.mvproject.datingapp.data.enums.profile.ProfileSmoke
import com.mvproject.datingapp.data.enums.profile.ProfileZodiac
import com.mvproject.datingapp.data.model.UserHeight
import com.mvproject.datingapp.data.model.UserWork
import com.mvproject.datingapp.ui.screens.main.questionaire.state.ProfileQuestionsState.Companion.completeContentProgress
import com.mvproject.datingapp.utils.STRING_EMPTY

data class ProfileQuestionsDataState(
    val profileAbout: String = STRING_EMPTY,
    val profileOrientation: ProfileOrientation = ProfileOrientation.ORIENTATION_HETERO,
    val profileMarital: ProfileMarital = ProfileMarital.MARITAL_COMPLICATED,
    val profileChildren: ProfileChildren = ProfileChildren.CHILDREN_NO_WANT,
    val currentStep: ProfileQuestionsState = ProfileQuestionsState.START,
    val userHeight: UserHeight = UserHeight(),
    val profileZodiac: ProfileZodiac = ProfileZodiac.ZODIAC_LEO,
    val profileAlcohol: ProfileAlcohol = ProfileAlcohol.ALCOHOL_OFTEN,
    val profileSmoke: ProfileSmoke = ProfileSmoke.SMOKE_SOMETIMES,
    val profilePsyOrientation: ProfilePsyOrientation = ProfilePsyOrientation.PSY_ORIENTATION_BETWEEN,
    val profileReligion: ProfileReligion = ProfileReligion.RELIGION_CATHOLICISM,
    val profileLanguages: List<ProfileLanguage> = ProfileLanguage.defaultLanguageList,
    val profilePets: List<ProfilePets> = ProfilePets.defaultPetList,
    val userWork: UserWork = UserWork(),
    val currentStepProgress: Float = ProfileQuestionsState.START.completeContentProgress(),
    val isComplete: Boolean = false
)
