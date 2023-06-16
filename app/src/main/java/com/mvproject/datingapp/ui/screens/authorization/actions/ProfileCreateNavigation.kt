/*
 * Create by Medvediev Viktor
 * last update: 09.06.23, 18:03
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.authorization.actions

sealed class ProfileCreateNavigation {
    object NavigateBack : ProfileCreateNavigation()
    object NavigateNext : ProfileCreateNavigation()
}