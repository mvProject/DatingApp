/*
 * Create by Medvediev Viktor
 * last update: 16.06.23, 16:58
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.main.profile.edit.action

sealed class EditPhotoAction {
    data class SetPhotoAsDefault(val index: Int) : EditPhotoAction()
    data class UploadPhoto(val index: Int, val uri: String) : EditPhotoAction()
    data class RemovePhoto(val index: Int) : EditPhotoAction()
}