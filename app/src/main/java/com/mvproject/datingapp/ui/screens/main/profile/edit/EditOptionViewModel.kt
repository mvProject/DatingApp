/*
 * Create by Medvediev Viktor
 * last update: 18.07.23, 18:01
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.main.profile.edit

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mvproject.datingapp.data.model.UserLocation
import com.mvproject.datingapp.data.repository.PreferenceRepository
import com.mvproject.datingapp.ui.screens.main.profile.edit.action.EditOptionAction
import com.mvproject.datingapp.ui.screens.main.profile.edit.navigation.EditOptionArgs
import com.mvproject.datingapp.ui.screens.main.profile.edit.state.EditOptionDataState
import com.mvproject.datingapp.ui.screens.main.profile.edit.state.EditProfileOption
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditOptionViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val preferenceRepository: PreferenceRepository
) : ViewModel() {
    private val editOptionArgs = EditOptionArgs(savedStateHandle)

    private val _profileState = MutableStateFlow(EditOptionDataState())
    val profileState = _profileState.asStateFlow()

    init {
        viewModelScope.launch {
            val user = preferenceRepository.getUser()

            _profileState.update {
                it.copy(
                    currentOption = EditProfileOption.valueOf(editOptionArgs.optionId),
                    profileInterest = user.interest,
                    profileAbout = user.profileAbout,
                    profileOrientation = user.profileOrientation,
                    profileMarital = user.profileMarital,
                    profileChildren = user.profileChildren,
                    profileHeight = user.profileHeight,
                    profileZodiac = user.profileZodiac,
                    profileAlcohol = user.profileAlcohol,
                    profileSmoke = user.profileSmoke,
                    profilePsyOrientation = user.profilePsyOrientation,
                    profileReligion = user.profileReligion,
                    profileLanguages = user.profileLanguages,
                    profilePets = user.profilePets,
                    profileWork = user.profileWork,
                    profileLocation = UserLocation.fromString(user.location)
                )
            }
        }
    }

    fun processAction(action: EditOptionAction) {
        viewModelScope.launch {
            val user = preferenceRepository.getUser()
            val updatedUser = when (action) {
                is EditOptionAction.UpdateInterest -> {
                    user.copy(interest = action.interest)
                }

                is EditOptionAction.UpdateLocation -> {
                    user.copy(location = action.location.toString())
                }

                is EditOptionAction.UpdateProfileAbout -> {
                    user.copy(profileAbout = action.data)
                }

                is EditOptionAction.UpdateProfileAlcohol -> {
                    user.copy(profileAlcohol = action.data)
                }

                is EditOptionAction.UpdateProfileChildren -> {
                    user.copy(profileChildren = action.data)
                }

                is EditOptionAction.UpdateProfileHeight -> {
                    user.copy(profileHeight = action.data)
                }

                is EditOptionAction.UpdateProfileLanguages -> {
                    user.copy(profileLanguages = action.data)
                }

                is EditOptionAction.UpdateProfileMarital -> {
                    user.copy(profileMarital = action.data)
                }

                is EditOptionAction.UpdateProfileOrientation -> {
                    user.copy(profileOrientation = action.data)
                }

                is EditOptionAction.UpdateProfilePets -> {
                    user.copy(profilePets = action.data)
                }

                is EditOptionAction.UpdateProfilePsyOrientation -> {
                    user.copy(profilePsyOrientation = action.data)
                }

                is EditOptionAction.UpdateProfileReligion -> {
                    user.copy(profileReligion = action.data)
                }

                is EditOptionAction.UpdateProfileSmoke -> {
                    user.copy(profileSmoke = action.data)
                }

                is EditOptionAction.UpdateProfileWork -> {
                    user.copy(profileWork = action.data)
                }

                is EditOptionAction.UpdateProfileZodiac -> {
                    user.copy(profileZodiac = action.data)
                }
            }

            preferenceRepository.saveUser(updatedUser)

            _profileState.update {
                it.copy(isComplete = true)
            }
        }


    }
}