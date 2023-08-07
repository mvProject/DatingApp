/*
 * Create by Medvediev Viktor
 * last update: 16.06.23, 16:58
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.main.questionaire.action

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

sealed class ProfileQuestionsAction {
    data class UpdateProfileAbout(val data: String) : ProfileQuestionsAction()
    data class UpdateProfileOrientation(val data: ProfileOrientation) : ProfileQuestionsAction()
    data class UpdateProfileMarital(val data: ProfileMarital) : ProfileQuestionsAction()
    data class UpdateProfileChildren(val data: ProfileChildren) : ProfileQuestionsAction()
    data class UpdateProfileHeight(val data: UserHeight) : ProfileQuestionsAction()
    data class UpdateProfileZodiac(val data: ProfileZodiac) : ProfileQuestionsAction()
    data class UpdateProfileAlcohol(val data: ProfileAlcohol) : ProfileQuestionsAction()
    data class UpdateProfileSmoke(val data: ProfileSmoke) : ProfileQuestionsAction()
    data class UpdateProfilePsyOrientation(val data: ProfilePsyOrientation) :
        ProfileQuestionsAction()

    data class UpdateProfileReligion(val data: ProfileReligion) : ProfileQuestionsAction()
    data class UpdateProfileLanguages(val data: List<ProfileLanguage>) : ProfileQuestionsAction()
    data class UpdateProfilePets(val data: List<ProfilePets>) : ProfileQuestionsAction()
    data class UpdateProfileWork(val data: UserWork) : ProfileQuestionsAction()
    object SaveProfileInfo : ProfileQuestionsAction()
    object NextStep : ProfileQuestionsAction()
    object PrevStep : ProfileQuestionsAction()
}