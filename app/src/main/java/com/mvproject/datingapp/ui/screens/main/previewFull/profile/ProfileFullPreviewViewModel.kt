/*
 * Create by Medvediev Viktor
 * last update: 11.08.23, 16:52
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.main.previewFull.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mvproject.datingapp.data.repository.PreferenceRepository
import com.mvproject.datingapp.ui.screens.main.previewFull.profile.state.ProfileFullPreviewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileFullPreviewViewModel @Inject constructor(
    private val preferenceRepository: PreferenceRepository,
) : ViewModel() {

    private val _profileFullPreviewState = MutableStateFlow(ProfileFullPreviewState())
    val profileFullPreviewState = _profileFullPreviewState.asStateFlow()

    init {
        viewModelScope.launch {
            val user = preferenceRepository.getUser()
            _profileFullPreviewState.update { current ->
                current.copy(
                    previewUser = user.copy(photos = user.photos.filter { it.isNotEmpty() })
                )
            }
        }
    }
}