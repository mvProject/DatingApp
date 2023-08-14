/*
 * Create by Medvediev Viktor
 * last update: 14.08.23, 09:43
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.main.previewFull.candidate

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.mvproject.datingapp.data.dummy.matchCandidateUsers
import com.mvproject.datingapp.data.repository.PreferenceRepository
import com.mvproject.datingapp.ui.screens.main.previewFull.candidate.navigation.CandidateFullPreviewArgs
import com.mvproject.datingapp.ui.screens.main.previewFull.candidate.state.CandidateFullPreviewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class CandidateFullPreviewViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val preferenceRepository: PreferenceRepository,
) : ViewModel() {
    private val candidateFullPreviewArgs = CandidateFullPreviewArgs(savedStateHandle)

    private val _candidateFullPreviewState = MutableStateFlow(CandidateFullPreviewState())
    val candidatePreviewState = _candidateFullPreviewState.asStateFlow()

    init {
        val id = candidateFullPreviewArgs.profileId
        matchCandidateUsers.firstOrNull { it.id.toString() == id }
            ?.let { user ->
                _candidateFullPreviewState.update { current ->
                    current.copy(
                        previewUser = user
                    )
                }
            }
    }
}