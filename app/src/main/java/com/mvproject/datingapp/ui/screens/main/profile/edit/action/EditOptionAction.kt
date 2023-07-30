/*
 * Create by Medvediev Viktor
 * last update: 16.06.23, 16:58
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.main.profile.edit.action

import com.mvproject.datingapp.data.enums.profile.ProfileAlcohol
import com.mvproject.datingapp.data.enums.profile.ProfileChildren
import com.mvproject.datingapp.data.enums.profile.ProfileInterest
import com.mvproject.datingapp.data.enums.profile.ProfileLanguage
import com.mvproject.datingapp.data.enums.profile.ProfileMarital
import com.mvproject.datingapp.data.enums.profile.ProfileOrientation
import com.mvproject.datingapp.data.enums.profile.ProfilePets
import com.mvproject.datingapp.data.enums.profile.ProfilePsyOrientation
import com.mvproject.datingapp.data.enums.profile.ProfileReligion
import com.mvproject.datingapp.data.enums.profile.ProfileSmoke
import com.mvproject.datingapp.data.enums.profile.ProfileZodiac
import com.mvproject.datingapp.data.model.UserHeight
import com.mvproject.datingapp.data.model.UserLocation
import com.mvproject.datingapp.data.model.UserWork

sealed class EditOptionAction {
    data class UpdateProfileAbout(val data: String) : EditOptionAction()
    data class UpdateLocation(val location: UserLocation) : EditOptionAction()
    data class UpdateInterest(val interest: ProfileInterest) : EditOptionAction()
    data class UpdateProfileOrientation(val data: ProfileOrientation) : EditOptionAction()
    data class UpdateProfileMarital(val data: ProfileMarital) : EditOptionAction()
    data class UpdateProfileChildren(val data: ProfileChildren) : EditOptionAction()
    data class UpdateProfileHeight(val data: UserHeight) : EditOptionAction()
    data class UpdateProfileZodiac(val data: ProfileZodiac) : EditOptionAction()
    data class UpdateProfileAlcohol(val data: ProfileAlcohol) : EditOptionAction()
    data class UpdateProfileSmoke(val data: ProfileSmoke) : EditOptionAction()
    data class UpdateProfilePsyOrientation(val data: ProfilePsyOrientation) :
        EditOptionAction()

    data class UpdateProfileReligion(val data: ProfileReligion) : EditOptionAction()
    data class UpdateProfileLanguages(val data: List<ProfileLanguage>) : EditOptionAction()
    data class UpdateProfilePets(val data: List<ProfilePets>) : EditOptionAction()
    data class UpdateProfileWork(val data: UserWork) : EditOptionAction()

}