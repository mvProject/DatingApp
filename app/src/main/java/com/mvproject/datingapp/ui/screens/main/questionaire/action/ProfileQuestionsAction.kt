/*
 * Create by Medvediev Viktor
 * last update: 16.06.23, 16:58
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.main.questionaire.action

sealed class ProfileQuestionsAction {
    object NextStep : ProfileQuestionsAction()
    object PrevStep : ProfileQuestionsAction()
}