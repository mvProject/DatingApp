/*
 * Create by Medvediev Viktor
 * last update: 23.06.23, 14:50
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.authorization.signin

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mvproject.datingapp.R
import com.mvproject.datingapp.data.enums.VerifyType
import com.mvproject.datingapp.ui.components.buttons.FacebookButton
import com.mvproject.datingapp.ui.components.buttons.GoogleButton
import com.mvproject.datingapp.ui.components.buttons.GradientButton
import com.mvproject.datingapp.ui.components.composable.rememberGoogleSignLauncher
import com.mvproject.datingapp.ui.components.input.InputPassword
import com.mvproject.datingapp.ui.components.input.InputText
import com.mvproject.datingapp.ui.components.loading.LoadingView
import com.mvproject.datingapp.ui.components.message.ErrorMessage
import com.mvproject.datingapp.ui.components.message.NoAccountField
import com.mvproject.datingapp.ui.screens.authorization.signin.action.SignInAction
import com.mvproject.datingapp.ui.screens.authorization.signin.state.SignInViewState
import com.mvproject.datingapp.ui.theme.DatingAppTheme
import com.mvproject.datingapp.ui.theme.dimens
import com.mvproject.datingapp.utils.STRING_EMPTY
import com.mvproject.datingapp.utils.WEIGHT_1
import com.mvproject.datingapp.utils.WEIGHT_2
import com.mvproject.datingapp.utils.isValidEmail
import com.mvproject.datingapp.utils.isValidPassword

@Composable
fun SignInScreen(
    viewModel: SignInViewModel,
    onNavigationForgotAccess: () -> Unit = {},
    onNavigationHome: () -> Unit = {},
    onNavigationSignUp: () -> Unit = {},
) {
    val viewState by viewModel.signInUiState.collectAsStateWithLifecycle()
    when (viewState) {
        is SignInViewState.Loading -> {
            LoadingView()
        }

        is SignInViewState.LoggedIn -> {
            LaunchedEffect(viewState) {
                onNavigationHome()
            }
        }

        is SignInViewState.NotLoggedIn -> {
            SignInView(
                signIntent = viewModel.googleSignIntent,
                onSignInAction = viewModel::processSignInAction,
                onForgotAccessAction = onNavigationForgotAccess,
                onSignUpAction = onNavigationSignUp
            )
        }
    }
}

@Composable
fun SignInView(
    signIntent: Intent = Intent(),
    onSignInAction: (SignInAction) -> Unit = {},
    onForgotAccessAction: () -> Unit = {},
    onSignUpAction: () -> Unit = {},
) {
    val context = LocalContext.current

    var isLoading by remember {
        mutableStateOf(false)
    }

    var isEmailVerificationFailed by remember {
        mutableStateOf(false)
    }

    var isPasswordVerificationFailed by remember {
        mutableStateOf(false)
    }

    val isVerificationFailed by remember {
        derivedStateOf {
            isEmailVerificationFailed || isPasswordVerificationFailed
        }
    }

    if (isLoading) {
        LoadingView()
    }

    Scaffold(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .padding(MaterialTheme.dimens.size24),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            var login by remember {
                mutableStateOf(STRING_EMPTY)
            }

            var password by remember {
                mutableStateOf(STRING_EMPTY)
            }

            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.app_logo),
                    contentDescription = null
                )
            }

            Spacer(modifier = Modifier.weight(WEIGHT_1))

            Text(
                text = stringResource(id = R.string.scr_auth_title_welcome),
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.displayLarge
            )

            Spacer(modifier = Modifier.weight(WEIGHT_2))

            InputText(
                modifier = Modifier.fillMaxWidth(),
                verifyType = VerifyType.EMAIL,
                isErrorEntered = isEmailVerificationFailed,
                onValueChange = { text ->
                    login = text
                    isEmailVerificationFailed = false
                    isPasswordVerificationFailed = false
                }
            )

            Spacer(modifier = Modifier.height(MaterialTheme.dimens.size12))

            InputPassword(
                modifier = Modifier.fillMaxWidth(),
                isErrorEntered = isPasswordVerificationFailed,
                onValueChange = { text ->
                    password = text
                    isEmailVerificationFailed = false
                    isPasswordVerificationFailed = false
                }
            )

            if (isVerificationFailed) {
                ErrorMessage(
                    text = stringResource(id = R.string.msg_error_login_validation)
                )
            }

            Spacer(modifier = Modifier.weight(WEIGHT_1))

            GradientButton(
                modifier = Modifier
                    .fillMaxWidth(),
                title = stringResource(id = R.string.btn_title_sign_in),
                onClick = {
                    if (login.isValidEmail() && password.isValidPassword()) {
                        onSignInAction(SignInAction.SignWithCredentialIn(login, password))
                    } else {
                        if (!login.isValidEmail()) {
                            isEmailVerificationFailed = true
                        }
                        if (!password.isValidPassword()) {
                            isPasswordVerificationFailed = true
                        }
                    }
                }
            )

            Spacer(modifier = Modifier.weight(WEIGHT_1))

            Text(
                modifier = Modifier.clickable {
                    onForgotAccessAction()
                },
                text = stringResource(id = R.string.btn_title_forgot_password),
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(modifier = Modifier.weight(WEIGHT_1))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Divider(
                    modifier = Modifier.width(MaterialTheme.dimens.size60),
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.width(MaterialTheme.dimens.size20))
                Text(
                    text = stringResource(id = R.string.scr_auth_title_or),
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.labelLarge
                )
                Spacer(modifier = Modifier.width(MaterialTheme.dimens.size20))
                Divider(
                    modifier = Modifier.width(MaterialTheme.dimens.size60),
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            Spacer(modifier = Modifier.weight(WEIGHT_1))

            val googleSignLauncher = rememberGoogleSignLauncher(
                onAuthComplete = { result ->
                    onSignInAction(SignInAction.SignWithGoogleIn(result))
                    isLoading = false
                },
                onAuthError = { code ->
                    Toast.makeText(
                        context,
                        "GoogleSign Error $code",
                        Toast.LENGTH_SHORT
                    ).show()
                    isLoading = false
                }
            )

            GoogleButton(
                modifier = Modifier
                    .fillMaxWidth(),
                onClick = {
                    googleSignLauncher.launch(signIntent)
                    isLoading = true
                }
            )

            Spacer(modifier = Modifier.height(MaterialTheme.dimens.size8))

            FacebookButton(
                modifier = Modifier
                    .fillMaxWidth(),
                onClick = {
                    Toast.makeText(
                        context,
                        "Facebook Sign Todo",
                        Toast.LENGTH_SHORT
                    ).show()
                    // todo implement
                    //onSignInAction(SignInAction.SignWithFacebookIn)
                }
            )

            Spacer(modifier = Modifier.weight(WEIGHT_2))

            NoAccountField(
                title = stringResource(id = R.string.scr_auth_title_no_account),
                actionTitle = stringResource(id = R.string.btn_title_sign_up),
                onAction = onSignUpAction
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSignInView() {
    DatingAppTheme {
        SignInView()
    }
}

@Preview(showBackground = true)
@Composable
fun DarkPreviewSignInView() {
    DatingAppTheme(darkTheme = true) {
        SignInView()
    }
}