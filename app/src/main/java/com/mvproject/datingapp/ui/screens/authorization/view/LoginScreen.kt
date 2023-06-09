/*
 * Create by Medvediev Viktor
 * last update: 08.06.23, 20:18
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.authorization.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mvproject.datingapp.R
import com.mvproject.datingapp.ui.screens.authorization.actions.LoginDataAction
import com.mvproject.datingapp.ui.screens.authorization.actions.LoginNavigation
import com.mvproject.datingapp.ui.screens.authorization.state.LoginDataState
import com.mvproject.datingapp.ui.screens.authorization.viewmodel.LoginViewModel
import com.mvproject.datingapp.ui.screens.destinations.RestoreAccessScreenDestination
import com.mvproject.datingapp.ui.screens.destinations.SignUpScreenDestination
import com.mvproject.datingapp.ui.theme.DatingAppTheme
import com.mvproject.datingapp.ui.theme.bluevioletDark
import com.mvproject.datingapp.ui.theme.dimens
import com.mvproject.datingapp.ui.theme.hotpink
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import timber.log.Timber

@RootNavGraph(start = true)
@Destination
@Composable
fun LoginScreen(
    navigator: DestinationsNavigator
) {
    val viewModel = viewModel<LoginViewModel>()
    val state by viewModel.loginDataState.collectAsStateWithLifecycle()

    LoginView(state = state,
        onLoginAction = viewModel::processAction,
        onLoginOptionAction = { action ->
            when (action) {
                is LoginNavigation.SignIn -> {
                    Timber.w("testing SignIn click")
                }

                is LoginNavigation.RestorePassword -> {
                    navigator.navigate(RestoreAccessScreenDestination())
                }

                is LoginNavigation.SignInFacebook -> {
                    Timber.w("testing SignInFacebook click")
                }

                is LoginNavigation.SignInGoogle -> {
                    Timber.w("testing SignInGoogle click")
                }

                is LoginNavigation.SignUp -> {
                    navigator.navigate(SignUpScreenDestination())
                }
            }
        })
}

@Composable
fun LoginView(
    state: LoginDataState,
    onLoginAction: (LoginDataAction) -> Unit = {},
    onLoginOptionAction: (LoginNavigation) -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                vertical = MaterialTheme.dimens.size24, horizontal = MaterialTheme.dimens.size24
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(5f)
                .align(Alignment.TopCenter),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.app_logo), contentDescription = null
                )
            }

            Spacer(modifier = Modifier.height(MaterialTheme.dimens.size24))

            Text(
                text = stringResource(id = R.string.scr_auth_title_welcome),
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.displayLarge
            )

            Spacer(modifier = Modifier.height(MaterialTheme.dimens.size24))

            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = state.email,
                onValueChange = {
                    onLoginAction(LoginDataAction.UpdateLogin(it))
                },
                placeholder = {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(id = R.string.hint_email),
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.onSurface,
                        textAlign = TextAlign.Center
                    )
                },
                textStyle = MaterialTheme.typography.bodyLarge,
                colors = TextFieldDefaults.colors(
                    focusedTextColor = MaterialTheme.colorScheme.onPrimary,
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    cursorColor = MaterialTheme.colorScheme.onPrimary,
                    focusedIndicatorColor = MaterialTheme.colorScheme.onPrimary
                )
            )

            Spacer(modifier = Modifier.height(MaterialTheme.dimens.size12))

            var pswdVisibility by remember {
                mutableStateOf(false)
            }

            val icon = if (pswdVisibility) {
                painterResource(id = R.drawable.ic_pswd_visible)
            } else {
                painterResource(id = R.drawable.ic_pswd_invisible)
            }

            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = state.password,
                onValueChange = {
                    onLoginAction(LoginDataAction.UpdatePassword(it))
                },
                placeholder = {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(id = R.string.hint_password),
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.onSurface,
                        textAlign = TextAlign.Center
                    )
                },
                trailingIcon = {
                    IconButton(onClick = {
                        pswdVisibility = !pswdVisibility
                    }) {
                        Icon(
                            painter = icon,
                            contentDescription = "",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                },
                visualTransformation = if (pswdVisibility) VisualTransformation.None
                else PasswordVisualTransformation(),
                textStyle = MaterialTheme.typography.bodyLarge,
                colors = TextFieldDefaults.colors(
                    focusedTextColor = MaterialTheme.colorScheme.onPrimary,
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    cursorColor = MaterialTheme.colorScheme.onPrimary,
                    focusedIndicatorColor = MaterialTheme.colorScheme.onPrimary
                )
            )

            Spacer(modifier = Modifier.height(MaterialTheme.dimens.size24))

            Button(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MaterialTheme.dimens.size8),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent
                ),
                contentPadding = PaddingValues(),
                onClick = { onLoginOptionAction(LoginNavigation.SignIn) }) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            shape = MaterialTheme.shapes.large, brush = Brush.linearGradient(
                                listOf(hotpink, bluevioletDark),
                                start = Offset(0f, Float.POSITIVE_INFINITY),
                                end = Offset(Float.POSITIVE_INFINITY, 0f)
                            )
                        )
                        .padding(
                            horizontal = MaterialTheme.dimens.size4,
                            vertical = MaterialTheme.dimens.size8
                        ), contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(id = R.string.btn_title_sign_in),
                        color = MaterialTheme.colorScheme.onTertiary,
                        style = MaterialTheme.typography.titleSmall
                    )
                }
            }

            Spacer(modifier = Modifier.height(MaterialTheme.dimens.size24))

            Text(
                modifier = Modifier.clickable {
                    onLoginOptionAction(LoginNavigation.RestorePassword)
                },
                text = stringResource(id = R.string.btn_title_forgot_password),
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(modifier = Modifier.height(MaterialTheme.dimens.size24))
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

            Spacer(modifier = Modifier.height(MaterialTheme.dimens.size24))
            ElevatedButton(
                onClick = {
                    onLoginOptionAction(LoginNavigation.SignInGoogle)
                },
                modifier = Modifier
                    .padding(MaterialTheme.dimens.size8)
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                ),
                shape = MaterialTheme.shapes.large
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo_google),
                    modifier = Modifier.size(MaterialTheme.dimens.size24),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.btn_title_sign_google),
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    style = MaterialTheme.typography.titleSmall,
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.height(MaterialTheme.dimens.size8))


            ElevatedButton(
                onClick = {
                    onLoginOptionAction(LoginNavigation.SignInFacebook)
                },
                modifier = Modifier
                    .padding(MaterialTheme.dimens.size8)
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.inversePrimary
                ),
                shape = MaterialTheme.shapes.large
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo_facebook),
                    modifier = Modifier.size(MaterialTheme.dimens.size24),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.btn_title_sign_facebook),
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    style = MaterialTheme.typography.titleSmall,
                    textAlign = TextAlign.Center
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(
                    vertical = MaterialTheme.dimens.size16,
                ), horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(id = R.string.scr_auth_title_no_account),
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.width(MaterialTheme.dimens.size8))
            Text(
                modifier = Modifier.clickable {
                    onLoginOptionAction(LoginNavigation.SignUp)
                },
                text = stringResource(id = R.string.btn_title_sign_up),
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewLoginView() {
    DatingAppTheme {
        LoginView(
            state = LoginDataState()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DarkPreviewLoginView() {
    DatingAppTheme(darkTheme = true) {
        LoginView(
            state = LoginDataState()
        )
    }
}