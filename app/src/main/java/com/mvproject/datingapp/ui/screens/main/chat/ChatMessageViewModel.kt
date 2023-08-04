/*
 * Create by Medvediev Viktor
 * last update: 18.07.23, 13:35
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.main.chat

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mvproject.datingapp.data.dummy.chatMessages
import com.mvproject.datingapp.data.dummy.sympathyUsers
import com.mvproject.datingapp.data.model.UserChatMessage
import com.mvproject.datingapp.data.repository.PreferenceRepository
import com.mvproject.datingapp.ui.screens.main.chat.action.ChatAction
import com.mvproject.datingapp.ui.screens.main.chat.navigation.ChatMessageArgs
import com.mvproject.datingapp.ui.screens.main.chat.state.ChatMessageState
import com.mvproject.datingapp.utils.DELAY_1_SEC
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatMessageViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val preferenceRepository: PreferenceRepository
) : ViewModel() {
    private val chatMessageArgs = ChatMessageArgs(savedStateHandle)

    private val _chatMessageState = MutableStateFlow(ChatMessageState())
    val chatMessageState = _chatMessageState.asStateFlow()

    private val _messages = mutableStateListOf<UserChatMessage>()
    val messages: List<UserChatMessage> = _messages

    private val senderUser =
        sympathyUsers.firstOrNull { it.id.toString() == chatMessageArgs.profileId }

    init {
        viewModelScope.launch {
            val current = preferenceRepository.getUser()
            val messageList = chatMessages(
                sender = senderUser?.id.toString(),
                current = current.uid
            )
            _messages.addAll(messageList)
            _chatMessageState.update {
                it.copy(
                    sender = senderUser,
                    logged = current.uid
                )
            }
        }
    }

    fun processAction(action: ChatAction) {
        when (action) {
            is ChatAction.AddMessage -> {
                viewModelScope.launch {
                    val newMessage = UserChatMessage(
                        message = action.message,
                        receiverId = senderUser?.id.toString(),
                        senderId = chatMessageState.value.logged,
                        sendDate = System.currentTimeMillis()
                    )

                    _messages.add(newMessage)

                    delay(DELAY_1_SEC)
                    val newMessageResponse = UserChatMessage(
                        message = action.message,
                        senderId = senderUser?.id.toString(),
                        receiverId = chatMessageState.value.logged,
                        sendDate = System.currentTimeMillis()
                    )
                    _messages.add(newMessageResponse)
                }
            }

            ChatAction.BlockUser -> {
                _chatMessageState.update {
                    it.copy(isUserBlocked = !chatMessageState.value.isUserBlocked)
                }
            }

            is ChatAction.ToggleLikeMessage -> {
                val indexCurrent = messages.indexOf(action.message)
                val current = messages[indexCurrent]
                val updated = current.copy(isMessageLiked = !current.isMessageLiked)
                _messages[indexCurrent] = updated
            }
        }

    }
}