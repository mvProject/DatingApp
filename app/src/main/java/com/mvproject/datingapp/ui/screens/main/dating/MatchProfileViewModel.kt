/*
 * Create by Medvediev Viktor
 * last update: 18.07.23, 13:35
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.main.dating

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mvproject.datingapp.data.dummy.matchCandidateUsers
import com.mvproject.datingapp.data.enums.profile.ProfileGender
import com.mvproject.datingapp.ui.screens.main.dating.navigation.MatchProfileArgs
import com.mvproject.datingapp.ui.screens.main.dating.state.MatchProfileState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MatchProfileViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val matchProfileArgs = MatchProfileArgs(savedStateHandle)

    private val _matchProfileState = MutableStateFlow(MatchProfileState())
    val matchProfileState = _matchProfileState.asStateFlow()

    init {
        val id = matchProfileArgs.matchProfileId

        viewModelScope.launch {
            val matchUser = matchCandidateUsers.firstOrNull { it.id.toString() == id }
            matchUser?.let { user ->
                val isFemale = user.gender == ProfileGender.FEMALE.name
                _matchProfileState.update {
                    it.copy(
                        id = user.id.toString(),
                        profilePhoto = user.profilePictureUrl,
                        isFemale = isFemale
                    )
                }
            }
        }
    }
}