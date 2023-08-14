/*
 * Create by Medvediev Viktor
 * last update: 18.07.23, 13:35
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.main.dating

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mvproject.datingapp.data.dummy.matchCandidateUsers
import com.mvproject.datingapp.data.repository.PreferenceRepository
import com.mvproject.datingapp.ui.screens.main.dating.action.DatingAction
import com.mvproject.datingapp.ui.screens.main.dating.state.DatingState
import com.mvproject.datingapp.utils.DELAY_500
import com.mvproject.datingapp.utils.INT_ZERO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DatingViewModel @Inject constructor(
    private val preferenceRepository: PreferenceRepository
) : ViewModel() {

    private val _datingState = MutableStateFlow(DatingState())
    val datingState = _datingState.asStateFlow()

    init {
        viewModelScope.launch {
            val user = preferenceRepository.getUser()

            _datingState.update {
                it.copy(
                    profileLogo = user.profilePictureUrl,
                    candidates = matchCandidateUsers
                )
            }
        }
        viewModelScope.launch(Dispatchers.IO) {
            preferenceRepository.getDatingFilters().collect { data ->
                _datingState.update {
                    it.copy(candidates = matchCandidateUsers)
                }
            }
        }
    }

    fun processAction(action: DatingAction) {
        when (action) {
            is DatingAction.Dislike -> {
                viewModelScope.launch {
                    _datingState.update {
                        it.copy(
                            dislikeAnimationState = true
                        )
                    }
                    delay(DELAY_500)
                    val candidates = datingState.value.candidates.toMutableList().also {
                        it.remove(action.user)
                    }

                    _datingState.update {
                        it.copy(
                            lastDislikeUser = action.user,
                            candidates = candidates,
                            dislikeAnimationState = false
                        )
                    }
                }
            }

            is DatingAction.Like -> {
                viewModelScope.launch {
                    _datingState.update {
                        it.copy(
                            likeAnimationState = true
                        )
                    }

                    delay(DELAY_500)

                    val candidates = datingState.value.candidates.toMutableList().also {
                        it.remove(action.user)
                    }
                    val matched = datingState.value.matchedUsers + action.user

                    val likedUser = if (action.user.isLiked) action.user else null

                    _datingState.update {
                        it.copy(
                            candidates = candidates,
                            matchedUsers = matched,
                            likeAnimationState = false,
                            lastBothLikeUser = likedUser
                        )
                    }
                }
            }

            DatingAction.LastUserRevenue -> {
                datingState.value.lastDislikeUser?.let { lastUser ->
                    val candidates = datingState.value.candidates.toMutableList().also {
                        it.add(INT_ZERO, lastUser)
                    }
                    _datingState.update {
                        it.copy(
                            lastDislikeUser = null,
                            candidates = candidates,
                        )
                    }
                }
            }

            DatingAction.BothMatchShown -> {
                _datingState.update {
                    it.copy(
                        lastBothLikeUser = null
                    )
                }
            }
        }
    }
}