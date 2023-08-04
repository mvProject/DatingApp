/*
 * Create by Medvediev Viktor
 * last update: 18.07.23, 13:35
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.main.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mvproject.datingapp.data.dummy.chatUsers
import com.mvproject.datingapp.data.dummy.sympathyUsers
import com.mvproject.datingapp.data.repository.PreferenceRepository
import com.mvproject.datingapp.ui.screens.main.chat.state.ChatState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val preferenceRepository: PreferenceRepository
) : ViewModel() {

    private val _chatState = MutableStateFlow(ChatState())
    val chatState = _chatState.asStateFlow()

    init {
        viewModelScope.launch {
            preferenceRepository.getUserFlow()
                .collect { user ->
                    _chatState.update {
                        it.copy(
                            profileLogo = user.profilePictureUrl,
                            sympathyUsers = sympathyUsers,
                            chatUsers = chatUsers
                        )
                    }
                }
        }
    }
}