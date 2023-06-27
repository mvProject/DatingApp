/*
 * Create by Medvediev Viktor
 * last update: 21.06.23, 19:47
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.main.profile

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mvproject.datingapp.dummy.DummyScreen
import com.mvproject.datingapp.navigation.AppRoutes
import com.mvproject.datingapp.ui.components.loading.LoadingView
import com.mvproject.datingapp.ui.screens.main.profile.state.ProfileViewState
import com.mvproject.datingapp.ui.theme.DatingAppTheme

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel,
    onNavigationLogout: () -> Unit,
) {
    val viewState by viewModel.profileUiState.collectAsStateWithLifecycle()
    when (viewState) {
        ProfileViewState.Loading -> {
            LoadingView()
        }

        ProfileViewState.LoggedIn -> {
            ProfileView(
                onLogout = viewModel::logoutProfile
            )
        }

        ProfileViewState.NotLoggedIn -> {
            LaunchedEffect(viewState) {
                onNavigationLogout()
            }
        }
    }
}

@Composable
fun ProfileView(
    onLogout: () -> Unit = {}
) {
    DummyScreen(
        title = AppRoutes.PROFILE.route,
        buttonFirstTitle = "Logout",
        buttonFirstAction = onLogout
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewProfileView() {
    DatingAppTheme {
        ProfileView()
    }
}

@Preview(showBackground = true)
@Composable
fun DarkPreviewProfileView() {
    DatingAppTheme(darkTheme = true) {
        ProfileView()
    }
}