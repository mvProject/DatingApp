/*
 * Create by Medvediev Viktor
 * last update: 21.06.23, 19:43
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.authorization.signup

import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import com.mvproject.datingapp.ui.components.input.otpDate.DateSelector
import com.mvproject.datingapp.ui.components.selectors.GenderSelector
import com.mvproject.datingapp.ui.components.selectors.LocationSelector
import com.mvproject.datingapp.ui.components.selectors.ProfileInterestSelector
import com.mvproject.datingapp.ui.components.selectors.image.ProfileImageSelector
import com.mvproject.datingapp.ui.screens.authorization.signup.actions.SignUpAction
import com.mvproject.datingapp.ui.screens.authorization.signup.state.SignUpProfileDataState
import com.mvproject.datingapp.ui.screens.authorization.signup.state.SignUpState
import com.mvproject.datingapp.ui.screens.authorization.signup.state.SignUpState.Companion.isStartState
import com.mvproject.datingapp.ui.theme.DatingAppTheme
import com.mvproject.datingapp.ui.theme.dimens
import com.mvproject.datingapp.utils.NAME_MAX_LENGTH
import com.mvproject.datingapp.utils.convertDateToClearFormat
import timber.log.Timber

@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel,
    onNavigateBack: () -> Unit,
    onNavigateNext: () -> Unit,
) {

    val state by viewModel.profileDataState.collectAsStateWithLifecycle()
    val timerState by viewModel.timerState.collectAsStateWithLifecycle()
    LaunchedEffect(state) {
        if (state.currentStep == SignUpState.CODE_VERIFY) {
            viewModel.launchTimer()
        }
    }
    SignUpView(
        state = state,
        timerState = timerState,
        onAction = viewModel::processAction,
        onNavigateBack = onNavigateBack,
        onNavigateNext = onNavigateNext
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpView(
    state: SignUpProfileDataState,
    timerState: TimerState = TimerState(),
    onAction: (SignUpAction) -> Unit = {},
    onNavigateBack: () -> Unit = {},
    onNavigateNext: () -> Unit = {}
) {

    BackHandler(true) {
        if (state.currentStep.isStartState()) {
            onNavigateBack()
        } else {
            onAction(SignUpAction.PrevStep)
        }
    }

    if (state.isComplete) {
        onNavigateNext()
    }
    val isPasswordSetDialogOpen = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .statusBarsPadding()
            .fillMaxSize()
    ) {
        val progress = animateFloatAsState(
            targetValue = state.currentStepProgress
        )

        LinearProgressIndicator(
            modifier = Modifier
                .fillMaxWidth()
                .height(MaterialTheme.dimens.size2),
            color = MaterialTheme.colorScheme.secondaryContainer,
            trackColor = MaterialTheme.colorScheme.outline,
            progress = progress.value
        )
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = {},
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                if (state.currentStep.isStartState()) {
                                    onNavigateBack()
                                } else {
                                    onAction(SignUpAction.PrevStep)
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
                )
            }
        ) { paddingValues ->
            Box(
                modifier = Modifier
                    .imePadding()
                    .padding(paddingValues)
                    .fillMaxSize()

            ) {
                Column(
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    when (state.currentStep) {
                        SignUpState.EMAIL -> {
                            InputTextWithDescription(
                                modifier = Modifier
                                    .padding(horizontal = MaterialTheme.dimens.size24),
                                initial = state.email,
                                title = stringResource(id = R.string.scr_auth_mail_select_title),
                                verifyType = VerifyType.EMAIL,
                                descriptionBottom = stringResource(id = R.string.scr_auth_mail_select_description),
                                privacyText = stringResource(id = R.string.scr_auth_privacy_email),
                                onSendClick = { text ->
                                    onAction(SignUpAction.UpdateEmail(text))
                                    onAction(SignUpAction.NextStep)
                                }
                            )
                        }

                        SignUpState.CODE_VERIFY -> {
                            CodeVerifier(
                                modifier = Modifier
                                    .padding(horizontal = MaterialTheme.dimens.size24),
                                timerState = timerState,
                                email = state.email,
                                code = state.code,
                                onCodeVerify = {
                                    onAction(SignUpAction.NextStep)
                                },
                                onChangeEmail = {
                                    onAction(SignUpAction.PrevStep)
                                },
                                onRetry = {
                                    onAction(SignUpAction.ResendCode)
                                }
                            )
                        }

                        SignUpState.PASSWORD -> {
                            SetPasswordWithConfirmation(
                                modifier = Modifier
                                    .padding(horizontal = MaterialTheme.dimens.size24),
                                title = stringResource(id = R.string.scr_auth_pswd_select_title),
                                btnTitle = stringResource(id = R.string.btn_title_continue),
                                verifyType = VerifyType.PASSWORD,
                                descriptionBottom = stringResource(id = R.string.scr_auth_pswd_select_description),
                                onConfirmed = { text ->
                                    onAction(SignUpAction.UpdatePassword(text))
                                    isPasswordSetDialogOpen.value = true
                                }
                            )

                        }

                        SignUpState.GENDER -> {
                            GenderSelector(
                                onGenderSelect = { gender ->
                                    onAction(SignUpAction.UpdateGender(gender))
                                    onAction(SignUpAction.NextStep)
                                }
                            )
                        }

                        SignUpState.NAME -> {
                            InputTextWithDescription(
                                modifier = Modifier
                                    .padding(horizontal = MaterialTheme.dimens.size24),
                                initial = state.name,
                                title = stringResource(id = R.string.scr_auth_name_select_title),
                                hint = stringResource(id = R.string.hint_name),
                                verifyType = VerifyType.NAME,
                                maxLength = NAME_MAX_LENGTH,
                                descriptionBottom = stringResource(id = R.string.scr_auth_name_select_description),
                                onSendClick = { text ->
                                    onAction(SignUpAction.UpdateName(text))
                                    onAction(SignUpAction.NextStep)
                                }
                            )
                        }

                        SignUpState.DATE -> {
                            DateSelector(
                                initial = state.birthdate.convertDateToClearFormat(),
                                title = stringResource(
                                    id = R.string.scr_auth_date_select_title,
                                    state.name
                                ),
                                privacyText = stringResource(id = R.string.scr_auth_privacy_birthdate),
                                onDateSelected = { date ->
                                    onAction(SignUpAction.UpdateBirthdate(date))
                                    onAction(SignUpAction.NextStep)
                                }
                            )
                        }

                        SignUpState.INTEREST -> {
                            ProfileInterestSelector(
                                selectedOption = stringResource(id = state.interest.title),
                                onOptionSelected = { interest ->
                                    Timber.w("testing selected $interest")
                                    onAction(SignUpAction.UpdateInterest(interest))
                                    onAction(SignUpAction.NextStep)
                                }
                            )
                        }

                        SignUpState.LOCATION -> {
                            LocationSelector(
                                onLocationSelected = { location ->
                                    onAction(SignUpAction.UpdateLocation(location))
                                    onAction(SignUpAction.NextStep)
                                }
                            )
                        }

                        SignUpState.PHOTO -> {
                            ProfileImageSelector(
                                onPhotoSelected = { photos ->
                                    onAction(SignUpAction.UpdatePhotos(photos))
                                }
                            )
                        }
                    }
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
                        onAction(SignUpAction.NextStep)
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSignUpView() {
    DatingAppTheme {
        SignUpView(SignUpProfileDataState())
    }
}

@Preview(showBackground = true)
@Composable
fun DarkPreviewSignUpView() {
    DatingAppTheme(darkTheme = true) {
        SignUpView(SignUpProfileDataState())
    }
}