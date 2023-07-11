/*
 * Create by Medvediev Viktor
 * last update: 16.06.23, 16:58
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.main.questionaire.action

import com.mvproject.datingapp.data.enums.ProfileOrientation

sealed class ProfileQuestionsAction {
    data class UpdateProfileAbout(val data: String) : ProfileQuestionsAction()
    data class UpdateProfileOrientation(val data: ProfileOrientation) : ProfileQuestionsAction()
    object NextStep : ProfileQuestionsAction()
    object PrevStep : ProfileQuestionsAction()
}