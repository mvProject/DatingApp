/*
 * Create by Medvediev Viktor
 * last update: 18.07.23, 13:35
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.main.likes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mvproject.datingapp.data.dummy.matchCandidateUsers
import com.mvproject.datingapp.data.repository.PreferenceRepository
import com.mvproject.datingapp.ui.screens.main.likes.action.LikesAction
import com.mvproject.datingapp.ui.screens.main.likes.state.LikesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LikesViewModel @Inject constructor(
    private val preferenceRepository: PreferenceRepository
) : ViewModel() {

    private val _likesState = MutableStateFlow(LikesState())
    val likesState = _likesState.asStateFlow()

    init {
        viewModelScope.launch {
            preferenceRepository.getActivationStateFlow()
                .collect { state ->
                    _likesState.update {
                        it.copy(
                            candidates = matchCandidateUsers,
                            isPlanActivated = state.status
                        )
                    }
                }
        }
    }

    fun processAction(action: LikesAction) {
        when (action) {
            is LikesAction.Dislike -> {
                viewModelScope.launch {
                    val candidates = likesState.value.candidates.toMutableList().also {
                        it.remove(action.user)
                    }

                    _likesState.update {
                        it.copy(
                            candidates = candidates,
                        )
                    }
                }
            }

            is LikesAction.Like -> {
                viewModelScope.launch {

                    val candidates = likesState.value.candidates.toMutableList().also {
                        it.remove(action.user)
                    }

                    val likedUser = if (action.user.isLiked) action.user else null

                    _likesState.update {
                        it.copy(
                            candidates = candidates,
                            lastBothLikeUser = likedUser
                        )
                    }
                }
            }

            LikesAction.BothMatchShown -> {
                _likesState.update {
                    it.copy(
                        lastBothLikeUser = null
                    )
                }
            }
        }
    }
}