/*
 * Create by Medvediev Viktor
 * last update: 14.08.23, 10:10
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.main.profile.preview

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mvproject.datingapp.R
import com.mvproject.datingapp.ui.components.composable.profile.ProfilePreviewShort
import com.mvproject.datingapp.ui.screens.main.profile.preview.state.ProfilePreviewState
import com.mvproject.datingapp.ui.theme.DatingAppTheme
import com.mvproject.datingapp.ui.theme.dimens

@Composable
fun ProfilePreviewScreen(
    viewModel: ProfilePreviewViewModel,
    onNavigationBack: () -> Unit = {},
    onNavigationDetail: () -> Unit = {}
) {
    val profilePreviewState by viewModel.profilePreviewState.collectAsStateWithLifecycle()

    ProfilePreviewView(
        state = profilePreviewState,
        onBackClick = onNavigationBack,
        onDetailClick = onNavigationDetail,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfilePreviewView(
    state: ProfilePreviewState = ProfilePreviewState(),
    onBackClick: () -> Unit = {},
    onDetailClick: () -> Unit = {},
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.screen_title_profile_preview),
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.headlineLarge,
                    )
                },
                navigationIcon = {
                    Row(
                        modifier = Modifier.clickable {
                            onBackClick()
                        },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_navigate_back),
                            contentDescription = "Back",
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                        Text(
                            text = stringResource(id = R.string.screen_title_profile_edit),
                            color = MaterialTheme.colorScheme.onPrimary,
                            style = MaterialTheme.typography.labelLarge,
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        ProfilePreviewShort(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(MaterialTheme.dimens.size16),
            isButtonEnabled = false,
            currentUser = state.previewUser,
            onDetailClick = onDetailClick
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewProfilePreviewView() {
    DatingAppTheme {
        ProfilePreviewView()
    }
}