/*
 * Create by Medvediev Viktor
 * last update: 18.07.23, 18:54
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.main.profile.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mvproject.datingapp.R
import com.mvproject.datingapp.ui.components.buttons.ColorButton
import com.mvproject.datingapp.ui.components.loading.LoadingView
import com.mvproject.datingapp.ui.components.menuoptions.OptionSettings
import com.mvproject.datingapp.ui.screens.main.profile.settings.state.SettingsState
import com.mvproject.datingapp.ui.screens.main.profile.view.state.ProfileViewState
import com.mvproject.datingapp.ui.theme.DatingAppTheme
import com.mvproject.datingapp.ui.theme.dimens
import com.mvproject.datingapp.utils.WEIGHT_1
import com.mvproject.datingapp.utils.convertDateToReadableFormat

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel,
    onNavigationLogout: () -> Unit = {},
    onNavigationBack: () -> Unit = {},
    onNavigationChange: () -> Unit = {}
) {
    val viewState by viewModel.profileUiState.collectAsStateWithLifecycle()
    val profileState by viewModel.profileState.collectAsStateWithLifecycle()
    when (viewState) {
        ProfileViewState.Loading -> {
            LoadingView()
        }

        ProfileViewState.LoggedIn -> {
            SettingsView(
                state = profileState,
                onLogoutClick = viewModel::logoutProfile,
                onChangeClick = onNavigationChange,
                onBackClick = onNavigationBack
            )
        }

        ProfileViewState.NotLoggedIn -> {
            LaunchedEffect(viewState) {
                onNavigationLogout()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsView(
    state: SettingsState = SettingsState(),
    onLogoutClick: () -> Unit = {},
    onChangeClick: () -> Unit = {},
    onBackClick: () -> Unit = {}
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.screen_title_profile_settings),
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.headlineLarge,
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = onBackClick
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_navigate_back),
                            contentDescription = "Back",
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }
                }
            )
        },
        containerColor = MaterialTheme.colorScheme.surfaceVariant
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(vertical = MaterialTheme.dimens.size8)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                modifier = Modifier.padding(start = MaterialTheme.dimens.size16),
                text = stringResource(id = R.string.profile_settings_main_title),
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.headlineSmall,
            )

            Spacer(modifier = Modifier.height(MaterialTheme.dimens.size8))

            OptionSettings(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.primary),
                title = stringResource(id = R.string.profile_settings_option_name),
                selected = state.profileName
            )
            OptionSettings(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.primary),
                title = stringResource(id = R.string.profile_settings_option_gender),
                selected = state.profileGender.name
            )
            OptionSettings(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.primary),
                title = stringResource(id = R.string.profile_settings_option_birthdate),
                selected = state.profileBirthDate.convertDateToReadableFormat()
            )
            OptionSettings(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.primary),
                title = stringResource(id = R.string.profile_settings_option_email),
                selected = state.profileEmail
            )

            Spacer(modifier = Modifier.height(MaterialTheme.dimens.size20))

            Text(
                modifier = Modifier.padding(start = MaterialTheme.dimens.size16),
                text = stringResource(id = R.string.profile_settings_notifications_title),
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.headlineSmall,
            )

            Spacer(modifier = Modifier.height(MaterialTheme.dimens.size8))

            OptionSettings(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.primary),
                title = stringResource(id = R.string.profile_settings_option_email_address)
            )
            OptionSettings(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.primary),
                title = stringResource(id = R.string.profile_settings_option_push_notifications)
            )

            Spacer(modifier = Modifier.height(MaterialTheme.dimens.size20))

            OptionSettings(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.primary),
                title = stringResource(id = R.string.profile_settings_option_change_password),
                onChange = onChangeClick
            )

            Spacer(modifier = Modifier.height(MaterialTheme.dimens.size20))

            OptionSettings(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.primary),
                title = stringResource(id = R.string.profile_settings_option_blocked_users)
            )

            Spacer(modifier = Modifier.height(MaterialTheme.dimens.size20))

            OptionSettings(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.primary),
                title = stringResource(id = R.string.profile_settings_option_delete_account),
                titleColor = MaterialTheme.colorScheme.onErrorContainer
            )

            Spacer(modifier = Modifier.weight(WEIGHT_1))

            Spacer(modifier = Modifier.height(MaterialTheme.dimens.size20))

            ColorButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = MaterialTheme.dimens.size16),
                title = stringResource(id = R.string.btn_title_sign_out),
                logo = painterResource(id = R.drawable.ic_sign_out),
                onClick = onLogoutClick
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSettingsView() {
    DatingAppTheme {
        SettingsView()
    }
}

@Preview(showBackground = true)
@Composable
fun DarkPreviewSettingsView() {
    DatingAppTheme(darkTheme = true) {
        SettingsView()
    }
}