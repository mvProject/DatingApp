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
import com.mvproject.datingapp.data.mapper.UserMapper.toPreview
import com.mvproject.datingapp.data.repository.PreferenceRepository
import com.mvproject.datingapp.ui.screens.main.dating.navigation.DatingProfileArgs
import com.mvproject.datingapp.ui.screens.main.dating.state.DatingProfileState
import com.mvproject.datingapp.utils.LOCAL_USER
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DatingProfileViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val preferenceRepository: PreferenceRepository,
) : ViewModel() {
    private val datingProfileArgs = DatingProfileArgs(savedStateHandle)

    private val _datingProfileState = MutableStateFlow(DatingProfileState())
    val datingProfileState = _datingProfileState.asStateFlow()

    init {
        val id = datingProfileArgs.profileId
        viewModelScope.launch {
            if (id == LOCAL_USER) {
                val local = preferenceRepository.getUser()
                _datingProfileState.update { current ->
                    current.copy(
                        isLocal = true,
                        previewUser = local.toPreview(),
                        userPhotos = local.photos.filter { it.isNotEmpty() }
                    )
                }
            } else {
                matchCandidateUsers.firstOrNull { it.id.toString() == id }
                    ?.let { user ->
                        _datingProfileState.update { current ->
                            current.copy(
                                previewUser = user.toPreview(),
                                matchPhotos = user.photos
                            )
                        }
                    }
            }
        }
    }
}