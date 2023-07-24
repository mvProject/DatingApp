/*
 * Create by Medvediev Viktor
 * last update: 18.07.23, 19:35
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.main.profile.changePassword

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mvproject.datingapp.R
import com.mvproject.datingapp.data.enums.VerifyType
import com.mvproject.datingapp.ui.components.dialog.InfoDialog
import com.mvproject.datingapp.ui.components.input.UpdatePasswordWithConfirmation
import com.mvproject.datingapp.ui.components.loading.LoadingView
import com.mvproject.datingapp.ui.screens.main.profile.changePassword.action.ChangePasswordAction
import com.mvproject.datingapp.ui.screens.main.profile.changePassword.state.ChangePasswordState
import com.mvproject.datingapp.ui.theme.DatingAppTheme
import com.mvproject.datingapp.ui.theme.dimens
import de.palm.composestateevents.EventEffect

@Composable
fun ChangePasswordScreen(
    viewModel: ChangePasswordViewModel,
    onNavigationBack: () -> Unit = {}
) {
    val profileState by viewModel.profileState.collectAsStateWithLifecycle()

    ChangePasswordView(
        state = profileState,
        onAction = viewModel::processAction,
        onBackClick = onNavigationBack
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChangePasswordView(
    state: ChangePasswordState = ChangePasswordState(),
    onAction: (ChangePasswordAction) -> Unit = {},
    onBackClick: () -> Unit = {}
) {
    val isPasswordSetDialogOpen = remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.screen_title_profile_change_password),
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
        }
    ) { paddingValues ->
        EventEffect(
            event = state.isPasswordWasChange,
            onConsumed = { onAction(ChangePasswordAction.SendPasswordComplete) }
        ) {
            isPasswordSetDialogOpen.value = true
        }

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(vertical = MaterialTheme.dimens.size8)
        ) {

            UpdatePasswordWithConfirmation(
                modifier = Modifier
                    .padding(horizontal = MaterialTheme.dimens.size24),
                verifyType = VerifyType.PASSWORD,
                descriptionTop = stringResource(id = R.string.scr_reset_password_description),
                onConfirmed = { text ->
                    onAction(ChangePasswordAction.UpdatePassword(text))
                    isPasswordSetDialogOpen.value = true
                }
            )
        }

        if (state.isLoading) {
            LoadingView()
        }

        // password was set
        InfoDialog(
            isDialogOpen = isPasswordSetDialogOpen,
            title = stringResource(id = R.string.dlg_psw_save_title),
            description = stringResource(id = R.string.dlg_psw_save_description),
            btnText = stringResource(id = R.string.btn_title_ok),
            image = painterResource(id = R.drawable.ic_pswd_send),
            onConfirm = {
                isPasswordSetDialogOpen.value = false
                onBackClick()
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewChangePasswordView() {
    DatingAppTheme {
        ChangePasswordView()
    }
}

@Preview(showBackground = true)
@Composable
fun DarkPreviewChangePasswordView() {
    DatingAppTheme(darkTheme = true) {
        ChangePasswordView()
    }
}