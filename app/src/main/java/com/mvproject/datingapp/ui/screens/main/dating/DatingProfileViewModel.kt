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
import com.mvproject.datingapp.data.model.UserLocation
import com.mvproject.datingapp.dummy.matchCandidateUsers
import com.mvproject.datingapp.ui.screens.main.dating.navigation.DatingProfileArgs
import com.mvproject.datingapp.ui.screens.main.dating.state.DatingProfileState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DatingProfileViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val datingProfileArgs = DatingProfileArgs(savedStateHandle)

    private val _datingProfileState = MutableStateFlow(DatingProfileState())
    val datingProfileState = _datingProfileState.asStateFlow()

    init {
        viewModelScope.launch {
            matchCandidateUsers.firstOrNull { it.id.toString() == datingProfileArgs.profileId }
                ?.let { user ->
                    _datingProfileState.update {
                        it.copy(
                            profile = user,
                            profileLocation = UserLocation.fromString(user.location),
                        )
                    }
                }
        }
    }
}