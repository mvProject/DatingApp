/*
 * Create by Medvediev Viktor
 * last update: 11.08.23, 16:51
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.main.profile.preview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mvproject.datingapp.data.repository.PreferenceRepository
import com.mvproject.datingapp.ui.screens.main.profile.preview.state.ProfilePreviewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfilePreviewViewModel @Inject constructor(
    private val preferenceRepository: PreferenceRepository
) : ViewModel() {

    private val _profilePreviewState = MutableStateFlow(ProfilePreviewState())
    val profilePreviewState = _profilePreviewState.asStateFlow()

    init {
        viewModelScope.launch {
            val user = preferenceRepository.getUser()
            _profilePreviewState.update { current ->
                current.copy(
                    previewUser = user.copy(photos = user.photos.filter { it.isNotEmpty() })
                )
            }
        }
    }
}