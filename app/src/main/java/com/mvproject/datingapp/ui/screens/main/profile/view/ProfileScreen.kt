/*
 * Create by Medvediev Viktor
 * last update: 18.07.23, 17:04
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.main.profile.view

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.mvproject.datingapp.R
import com.mvproject.datingapp.ui.components.buttons.ColorButton
import com.mvproject.datingapp.ui.components.info.InterestInfo
import com.mvproject.datingapp.ui.components.loading.LoadingView
import com.mvproject.datingapp.ui.screens.main.profile.view.state.ProfileDataState
import com.mvproject.datingapp.ui.screens.main.profile.view.state.ProfileViewState
import com.mvproject.datingapp.ui.theme.DatingAppTheme
import com.mvproject.datingapp.ui.theme.bluevioletDark
import com.mvproject.datingapp.ui.theme.dimens
import com.mvproject.datingapp.ui.theme.hotpink
import com.mvproject.datingapp.utils.STRING_COMA_SPACE
import com.mvproject.datingapp.utils.WEIGHT_1
import com.mvproject.datingapp.utils.convertDateToReadableFormat
import com.mvproject.datingapp.utils.gradientBackground

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel,
    onNavigationLogout: () -> Unit = {},
    onNavigationSettings: () -> Unit = {},
    onNavigationActivation: () -> Unit = {},
    onNavigationEdit: () -> Unit = {}
) {
    val viewState by viewModel.profileUiState.collectAsStateWithLifecycle()
    val profileState by viewModel.profileState.collectAsStateWithLifecycle()
    when (viewState) {
        ProfileViewState.Loading -> {
            LoadingView()
        }

        ProfileViewState.LoggedIn -> {
            ProfileView(
                state = profileState,
                onActivateClick = onNavigationActivation,
                onSettingsClick = onNavigationSettings,
                onEditClick = onNavigationEdit
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
fun ProfileView(
    state: ProfileDataState = ProfileDataState(),
    onActivateClick: () -> Unit = {},
    onSettingsClick: () -> Unit = {},
    onEditClick: () -> Unit = {}
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.screen_title_profile),
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.displayLarge
                    )
                },
                actions = {
                    IconButton(onClick = onSettingsClick) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_settings),
                            contentDescription = stringResource(id = R.string.screen_title_profile),
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }
            )
        },
        bottomBar = { NavigationBar() {} },
        contentWindowInsets = WindowInsets.navigationBars
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(vertical = MaterialTheme.dimens.size8)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .width(MaterialTheme.dimens.size120)
                    .height(MaterialTheme.dimens.size126)
            ) {
                AsyncImage(
                    model = Uri.parse(state.profileImage),
                    contentDescription = state.profileName,
                    modifier = Modifier
                        .size(MaterialTheme.dimens.size120)
                        .border(
                            MaterialTheme.dimens.size3,
                            color = MaterialTheme.colorScheme.secondary,
                            shape = CircleShape
                        )
                        .padding(MaterialTheme.dimens.size6)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )

                Text(
                    modifier = Modifier
                        .wrapContentWidth()
                        .background(
                            color = MaterialTheme.colorScheme.secondary,
                            shape = RoundedCornerShape(MaterialTheme.dimens.size100)
                        )
                        .padding(
                            horizontal = MaterialTheme.dimens.size5
                        )
                        .align(Alignment.BottomCenter),
                    text = stringResource(id = R.string.profile_complete_percentage, 80),
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.labelSmall,
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.height(MaterialTheme.dimens.size18))

            Text(
                modifier = Modifier.wrapContentWidth(),
                text = buildAnnotatedString {
                    append(state.profileName)
                    append(STRING_COMA_SPACE)
                    withStyle(
                        style = SpanStyle(
                            fontWeight = MaterialTheme.typography.labelSmall.fontWeight
                        )
                    ) {
                        append(state.profileAge.toString())
                    }
                },
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.titleLarge,
                fontSize = MaterialTheme.dimens.font24
            )

            Spacer(modifier = Modifier.height(MaterialTheme.dimens.size10))

            InterestInfo(
                modifier = Modifier.wrapContentWidth(),
                selectedInterest = state.profileInterest
            )

            Spacer(modifier = Modifier.height(MaterialTheme.dimens.size16))

            ColorButton(
                modifier = Modifier.wrapContentWidth(),
                title = stringResource(id = R.string.btn_title_profile_edit),
                logo = painterResource(id = R.drawable.ic_profile_edit),
                onClick = onEditClick
            )

            Spacer(modifier = Modifier.height(MaterialTheme.dimens.size16))

            Divider(
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(MaterialTheme.dimens.size16))

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = MaterialTheme.dimens.size16)
                    .clip(RoundedCornerShape(MaterialTheme.dimens.size16))
                    .gradientBackground(listOf(hotpink, bluevioletDark), 45f)
                    .padding(
                        vertical = MaterialTheme.dimens.size16,
                        horizontal = MaterialTheme.dimens.size24
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_profile_activate),
                    contentDescription = stringResource(id = R.string.unlock_profile_title)
                )

                Text(
                    text = stringResource(id = R.string.unlock_profile_title),
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.titleLarge,
                    fontSize = MaterialTheme.dimens.font24,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.weight(WEIGHT_1))

                Text(
                    text = stringResource(id = R.string.unlock_profile_description),
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.labelMedium,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(MaterialTheme.dimens.size24))

                val buttonColor = if (state.activationStatus)
                    MaterialTheme.colorScheme.primary.copy(alpha = 0.25f)
                else
                    MaterialTheme.colorScheme.onPrimary

                val buttonTitle = if (state.activationStatus)
                    stringResource(
                        id = R.string.btn_title_profile_activate_expires,
                        state.activationExpires.convertDateToReadableFormat()
                    )
                else
                    stringResource(
                        id = R.string.btn_title_profile_activate,
                        state.activationPrice
                    )

                ColorButton(
                    modifier = Modifier.fillMaxWidth(),
                    title = buttonTitle,
                    btnColor = buttonColor,
                    onClick = onActivateClick
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewProfileView() {
    DatingAppTheme {
        ProfileView()
    }
}