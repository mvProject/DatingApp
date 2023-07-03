/*
 * Create by Medvediev Viktor
 * last update: 26.06.23, 14:47
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.authorization.restoreAccess

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mvproject.datingapp.R
import com.mvproject.datingapp.data.enums.VerifyType
import com.mvproject.datingapp.data.state.TimerState
import com.mvproject.datingapp.ui.components.dialog.InfoDialog
import com.mvproject.datingapp.ui.components.input.InputTextWithDescription
import com.mvproject.datingapp.ui.components.input.SetPasswordWithConfirmation
import com.mvproject.datingapp.ui.components.input.otpCode.CodeVerifier
import com.mvproject.datingapp.ui.components.loading.LoadingView
import com.mvproject.datingapp.ui.screens.authorization.restoreAccess.action.RestoreAccessAction
import com.mvproject.datingapp.ui.screens.authorization.restoreAccess.state.RestoreAccessDataState
import com.mvproject.datingapp.ui.screens.authorization.restoreAccess.state.RestoreAccessState
import com.mvproject.datingapp.ui.screens.authorization.restoreAccess.state.RestoreAccessState.Companion.isLastState
import com.mvproject.datingapp.ui.screens.authorization.restoreAccess.state.RestoreAccessState.Companion.isStartState
import com.mvproject.datingapp.ui.theme.DatingAppTheme
import com.mvproject.datingapp.ui.theme.dimens
import de.palm.composestateevents.EventEffect

@Composable
fun RestoreAccessScreen(
    viewModel: RestoreAccessViewModel,
    onNavigateNext: () -> Unit = {},
    onNavigateBack: () -> Unit = {}
) {
    val state by viewModel.restoreAccessDataState.collectAsStateWithLifecycle()
    val timerState by viewModel.timerState.collectAsStateWithLifecycle()

    LaunchedEffect(state) {
        if (state.currentStep == RestoreAccessState.CODE_VERIFY) {
            viewModel.launchTimer()
        }
    }
    RestoreAccessView(
        state = state,
        timerState = timerState,
        onAction = viewModel::processAction,
        onNavigateBack = onNavigateBack,
        onNavigateNext = onNavigateNext
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RestoreAccessView(
    state: RestoreAccessDataState,
    timerState: TimerState = TimerState(),
    onAction: (RestoreAccessAction) -> Unit = {},
    onNavigateNext: () -> Unit = {},
    onNavigateBack: () -> Unit = {}
) {
    val isCodeSendDialogOpen = remember { mutableStateOf(false) }
    val isPasswordSetDialogOpen = remember { mutableStateOf(false) }

    BackHandler(true) {
        if (state.currentStep.isStartState()) {
            onNavigateBack()
        } else {
            onAction(RestoreAccessAction.PrevStep)
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    if (state.currentStep.isLastState()) {
                        Text(
                            text = stringResource(id = R.string.scr_reset_password_title),
                            color = MaterialTheme.colorScheme.onPrimary,
                            style = MaterialTheme.typography.headlineLarge
                        )
                    }
                },
                navigationIcon = {
                    if (!state.currentStep.isLastState()) {
                        IconButton(
                            onClick = {
                                if (state.currentStep.isStartState()) {
                                    onNavigateBack()
                                } else {
                                    onAction(RestoreAccessAction.PrevStep)
                                }
                            }
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_navigate_back),
                                contentDescription = "Back",
                                tint = MaterialTheme.colorScheme.onBackground
                            )
                        }
                    }
                }
            )
        }
    ) { paddingValues ->
        EventEffect(
            event = state.isCodeWasSend,
            onConsumed = { onAction(RestoreAccessAction.SendCodeComplete) }
        ) {
            isCodeSendDialogOpen.value = true
        }

        EventEffect(
            event = state.isPasswordWasChange,
            onConsumed = { onAction(RestoreAccessAction.SendPasswordComplete) }
        ) {
            isPasswordSetDialogOpen.value = true
        }

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(horizontal = MaterialTheme.dimens.size24),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            when (state.currentStep) {
                RestoreAccessState.EMAIL_REQUEST -> {
                    InputTextWithDescription(
                        initial = state.email,
                        title = stringResource(id = R.string.scr_request_reset_password_title),
                        verifyType = VerifyType.EMAIL,
                        descriptionTop = stringResource(id = R.string.scr_request_reset_password_description),
                        onSendClick = { text ->
                            onAction(RestoreAccessAction.UpdateEmail(text))
                            onAction(RestoreAccessAction.SendCode)
                        }
                    )
                }

                RestoreAccessState.CODE_VERIFY -> {
                    CodeVerifier(
                        modifier = Modifier.fillMaxWidth(),
                        timerState = timerState,
                        email = state.email,
                        code = state.code,
                        onCodeVerify = {
                            onAction(RestoreAccessAction.NextStep)
                        },
                        onChangeEmail = {
                            onAction(RestoreAccessAction.PrevStep)
                        },
                        onRetry = {
                            onAction(RestoreAccessAction.ResendCode)
                        }
                    )
                }

                RestoreAccessState.PASSWORD_SET -> {
                    SetPasswordWithConfirmation(
                        verifyType = VerifyType.PASSWORD,
                        descriptionTop = stringResource(id = R.string.scr_reset_password_description),
                        onConfirmed = { text ->
                            onAction(RestoreAccessAction.UpdatePassword(text))
                            onAction(RestoreAccessAction.SendPassword)
                        }
                    )
                }
            }
        }

        if (state.isLoading) {
            LoadingView()
        }

        InfoDialog(
            isDialogOpen = isCodeSendDialogOpen,
            title = stringResource(id = R.string.dlg_psw_sent_title),
            description = stringResource(id = R.string.dlg_psw_sent_description),
            btnText = stringResource(id = R.string.btn_title_ok),
            image = painterResource(id = R.drawable.ic_code_send),
            onConfirm = {
                isCodeSendDialogOpen.value = false
                onAction(RestoreAccessAction.NextStep)
            }
        )

        InfoDialog(
            isDialogOpen = isPasswordSetDialogOpen,
            title = stringResource(id = R.string.dlg_psw_save_title),
            description = stringResource(id = R.string.dlg_psw_save_description),
            btnText = stringResource(id = R.string.btn_title_ok),
            image = painterResource(id = R.drawable.ic_pswd_send),
            onConfirm = {
                isPasswordSetDialogOpen.value = false
                onNavigateNext()
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewRestoreAccessView() {
    DatingAppTheme {
        RestoreAccessView(state = RestoreAccessDataState())
    }
}

@Preview(showBackground = true)
@Composable
fun DarkPreviewRestoreAccessView() {
    DatingAppTheme(darkTheme = true) {
        RestoreAccessView(state = RestoreAccessDataState())
    }
}