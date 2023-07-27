/*
 * Create by Medvediev Viktor
 * last update: 18.07.23, 18:01
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.main.profile.edit

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mvproject.datingapp.data.model.UserLocation
import com.mvproject.datingapp.data.repository.PreferenceRepository
import com.mvproject.datingapp.data.repository.StorageRepository
import com.mvproject.datingapp.ui.screens.main.profile.edit.action.EditPhotoAction
import com.mvproject.datingapp.ui.screens.main.profile.edit.state.EditDataState
import com.mvproject.datingapp.utils.STRING_EMPTY
import com.mvproject.datingapp.utils.STRING_SLASH
import com.mvproject.datingapp.utils.prepareImages
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class EditViewModel @Inject constructor(
    private val preferenceRepository: PreferenceRepository,
    private val storageRepository: StorageRepository
) : ViewModel() {

    private val _profileState = MutableStateFlow(EditDataState())
    val profileState = _profileState.asStateFlow()

    private val _userPhotos = mutableStateListOf<String>()
    val userPhotos: List<String> = _userPhotos

    init {
        viewModelScope.launch {
            val photos = storageRepository.getAllFiles()
            Timber.e("testing photos count:${photos.count()}")
        }

        viewModelScope.launch {
            _userPhotos.addAll(prepareImages(preferenceRepository.getUser().photos))
        }

        viewModelScope.launch {
            preferenceRepository.getUserFlow()
                .collect { user ->
                    _profileState.update {
                        it.copy(
                            currentProfile = user,
                            profileInterest = user.interest,
                            profileLocation = UserLocation.fromString(user.location),
                            profileHeight = user.profileHeight.height
                        )
                    }
                }
        }
    }

    fun processAction(action: EditPhotoAction) {
        when (action) {
            is EditPhotoAction.RemovePhoto -> {
                removePhoto(index = action.index)
            }

            is EditPhotoAction.SetPhotoAsDefault -> {
                setDefault(index = action.index)
            }

            is EditPhotoAction.UploadPhoto -> {
                updatePhoto(
                    index = action.index,
                    uri = action.uri
                )
            }
        }
    }

    private fun setDefault(index: Int) {
        val default = userPhotos[index]
        viewModelScope.launch {
            val user = profileState.value.currentProfile
            val updatedUser = user.copy(profilePictureUrl = default)
            preferenceRepository.saveUser(user = updatedUser)
        }
    }

    private fun removePhoto(index: Int) {
        val photoForDelete = userPhotos[index].substringAfterLast(STRING_SLASH)
        _userPhotos[index] = STRING_EMPTY
        Timber.w("testing removePhoto photoForDelete:$photoForDelete")
        storageRepository.deleteFile(photoForDelete)

        viewModelScope.launch {
            val user = profileState.value.currentProfile
            val default = if (user.profilePictureUrl in userPhotos) {
                user.profilePictureUrl
            } else {
                userPhotos.firstOrNull { it.isNotEmpty() } ?: STRING_EMPTY
            }
            val updatedUser = user.copy(
                profilePictureUrl = default,
                photos = userPhotos
            )
            preferenceRepository.saveUser(user = updatedUser)
        }
    }

    private fun updatePhoto(index: Int, uri: String) {
        viewModelScope.launch {
            val user = profileState.value.currentProfile
            val image = updatedImage(user.email, uri)
            val photoForDelete = userPhotos[index].substringAfterLast(STRING_SLASH)
            Timber.w("testing updatePhoto photoForDelete:$photoForDelete")
            storageRepository.deleteFile(photoForDelete)
            _userPhotos[index] = image
            val updatedUser = user.copy(photos = userPhotos)
            preferenceRepository.saveUser(user = updatedUser)
        }
    }

    private suspend fun updatedImage(email: String, image: String): String =
        withContext(Dispatchers.IO) {
            return@withContext storageRepository.setUserPhoto(
                username = email,
                source = image
            )
        }
}