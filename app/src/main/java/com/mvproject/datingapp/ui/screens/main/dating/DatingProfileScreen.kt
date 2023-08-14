/*
 * Create by Medvediev Viktor
 * last update: 18.07.23, 17:04
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.main.dating

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.mvproject.datingapp.R
import com.mvproject.datingapp.ui.components.ChipInfo
import com.mvproject.datingapp.ui.components.indicators.StoryIndicator
import com.mvproject.datingapp.ui.components.info.ShortProfileInfo
import com.mvproject.datingapp.ui.screens.main.dating.state.DatingProfileState
import com.mvproject.datingapp.ui.theme.DatingAppTheme
import com.mvproject.datingapp.ui.theme.dimens
import com.mvproject.datingapp.utils.FLOAT_ZERO
import com.mvproject.datingapp.utils.INT_ZERO
import com.mvproject.datingapp.utils.STEP_1
import com.mvproject.datingapp.utils.calculatAgeMillis

@Composable
fun DatingProfileScreen(
    viewModel: DatingProfileViewModel,
    onNavigationBack: () -> Unit = {}
) {
    val datingState by viewModel.datingProfileState.collectAsStateWithLifecycle()

    DatingProfileView(
        state = datingState,
        onBackClick = onNavigationBack
    )
}

@OptIn(ExperimentalLayoutApi::class, ExperimentalFoundationApi::class)
@Composable
fun DatingProfileView(
    state: DatingProfileState = DatingProfileState(),
    onBackClick: () -> Unit = {}
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .verticalScroll(rememberScrollState())
            .padding(
                horizontal = MaterialTheme.dimens.size8,
                vertical = MaterialTheme.dimens.size16
            )
    ) {
        val pages = if (state.isLocal)
            state.userPhotos
        else
            state.matchPhotos

        val pagerState = rememberPagerState(
            initialPage = INT_ZERO,
            initialPageOffsetFraction = FLOAT_ZERO
        ) {
            pages.size
        }
        Box(
            modifier = Modifier
                .height(MaterialTheme.dimens.size420)
                .fillMaxWidth()
                .clip(MaterialTheme.shapes.medium)
        ) {

            HorizontalPager(
                modifier = Modifier.fillMaxWidth(),
                state = pagerState,
                pageNestedScrollConnection = PagerDefaults
                    .pageNestedScrollConnection(Orientation.Horizontal),
                pageContent = { position ->
                    Card(
                        modifier = Modifier.fillMaxSize(),
                        shape = MaterialTheme.shapes.medium
                    ) {
                        if (state.isLocal) {
                            AsyncImage(
                                modifier = Modifier.fillMaxSize(),
                                model = state.userPhotos[position],
                                contentDescription = null,
                                contentScale = ContentScale.FillWidth
                            )
                        } else {
                            Image(
                                modifier = Modifier.fillMaxSize(),
                                painter = painterResource(id = state.matchPhotos[position]),
                                contentDescription = null,
                                contentScale = ContentScale.FillWidth
                            )
                        }
                    }
                }
            )

            StoryIndicator(
                modifier = Modifier
                    .padding(top = MaterialTheme.dimens.size8)
                    .align(Alignment.TopStart),
                totalDots = pages.size - STEP_1,
                selectedIndex = pagerState.currentPage
            )

            Icon(
                modifier = Modifier
                    .padding(
                        top = MaterialTheme.dimens.size16,
                        end = MaterialTheme.dimens.size8
                    )
                    .size(MaterialTheme.dimens.size40)
                    .clickable {
                        onBackClick()
                    }
                    .align(Alignment.TopEnd),
                painter = painterResource(id = R.drawable.ic_close),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
        }

        Spacer(modifier = Modifier.height(MaterialTheme.dimens.size16))

        ShortProfileInfo(
            modifier = Modifier
                .padding(horizontal = MaterialTheme.dimens.size8),
            profileName = state.previewUser.name,
            profileAge = calculatAgeMillis(state.previewUser.birthdate),
            profileInterest = state.previewUser.interest
        )

        Spacer(modifier = Modifier.height(MaterialTheme.dimens.size16))

        Divider(
            color = MaterialTheme.colorScheme.onSurface
        )

        if (state.previewUser.profileAbout.isNotEmpty()) {
            Spacer(modifier = Modifier.height(MaterialTheme.dimens.size16))

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = MaterialTheme.dimens.size8),
                text = stringResource(id = R.string.profile_edit_option_about_me),
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = MaterialTheme.dimens.font14
            )
            Spacer(modifier = Modifier.height(MaterialTheme.dimens.size16))

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = MaterialTheme.dimens.size8),
                text = state.previewUser.profileAbout,
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onPrimary
            )
            Spacer(modifier = Modifier.height(MaterialTheme.dimens.size16))

            Divider(
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        Spacer(modifier = Modifier.height(MaterialTheme.dimens.size16))

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MaterialTheme.dimens.size8),
            text = stringResource(id = R.string.profile_edit_option_basic_information),
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = MaterialTheme.dimens.font14
        )
        Spacer(modifier = Modifier.height(MaterialTheme.dimens.size16))

        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MaterialTheme.dimens.size8),
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.dimens.size8),
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.dimens.size8)
        ) {
            if (state.previewUser.profileOrientation.isValueSet) {
                ChipInfo(
                    text = stringResource(id = state.previewUser.profileOrientation.title),
                    logo = painterResource(id = state.previewUser.profileOrientation.logo)
                )
            }

            if (state.previewUser.profileMarital.isValueSet) {
                ChipInfo(
                    text = stringResource(id = state.previewUser.profileMarital.title),
                    logo = painterResource(id = state.previewUser.profileMarital.logo)
                )
            }

            if (!state.previewUser.profileHeight.isHeightNotVisible) {
                ChipInfo(
                    text = stringResource(
                        id = R.string.profile_edit_option_height_data,
                        state.previewUser.profileHeight.height
                    ),
                    logo = painterResource(id = R.drawable.ic_edit_height)
                )
            }

            if (state.previewUser.profileChildren.isValueSet) {
                ChipInfo(
                    text = stringResource(id = state.previewUser.profileChildren.title),
                    logo = painterResource(id = state.previewUser.profileChildren.logo)
                )
            }

            if (state.previewUser.profileZodiac.isValueSet) {
                ChipInfo(
                    text = stringResource(id = state.previewUser.profileZodiac.title),
                    logo = painterResource(id = state.previewUser.profileZodiac.logo)
                )
            }

            if (state.previewUser.profileAlcohol.isValueSet) {
                ChipInfo(
                    text = stringResource(id = state.previewUser.profileAlcohol.title),
                    logo = painterResource(id = state.previewUser.profileAlcohol.logo)
                )
            }

            if (state.previewUser.profileSmoke.isValueSet) {
                ChipInfo(
                    text = stringResource(id = state.previewUser.profileSmoke.title),
                    logo = painterResource(id = state.previewUser.profileSmoke.logo)
                )
            }

            if (state.previewUser.profilePsyOrientation.isValueSet) {
                ChipInfo(
                    text = stringResource(id = state.previewUser.profilePsyOrientation.title),
                    logo = painterResource(id = state.previewUser.profilePsyOrientation.logo)
                )
            }

            if (state.previewUser.profileReligion.isValueSet) {
                ChipInfo(
                    text = stringResource(id = state.previewUser.profileReligion.title),
                    logo = painterResource(id = state.previewUser.profileReligion.logo)
                )
            }

            state.previewUser.profileLanguages.forEach { lang ->
                ChipInfo(
                    text = stringResource(id = lang.title),
                    logo = painterResource(id = lang.logo)
                )
            }

            state.previewUser.profilePets.forEach { pet ->
                ChipInfo(
                    text = stringResource(id = pet.title),
                    logo = painterResource(id = pet.logo)
                )
            }
        }

        Spacer(modifier = Modifier.height(MaterialTheme.dimens.size16))


        Divider(
            color = MaterialTheme.colorScheme.onSurface
        )

        if (!state.previewUser.profileWork.isBothEmpty) {
            Spacer(modifier = Modifier.height(MaterialTheme.dimens.size16))
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = MaterialTheme.dimens.size8),
                text = stringResource(id = R.string.profile_edit_option_work),
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = MaterialTheme.dimens.font14
            )
            Spacer(modifier = Modifier.height(MaterialTheme.dimens.size16))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = MaterialTheme.dimens.size8),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_edit_work),
                    contentDescription = stringResource(id = R.string.profile_edit_option_work)
                )
                Spacer(modifier = Modifier.width(MaterialTheme.dimens.size8))
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = MaterialTheme.dimens.size8),
                    text = if (state.previewUser.profileWork.isBothFilled) {
                        stringResource(
                            id = R.string.profile_edit_option_work_data,
                            state.previewUser.profileWork.jobTitle,
                            state.previewUser.profileWork.jobCompany
                        )
                    } else {
                        when {
                            state.previewUser.profileWork.jobTitle.isNotEmpty() ->
                                state.previewUser.profileWork.jobTitle

                            state.previewUser.profileWork.jobCompany.isNotEmpty() ->
                                state.previewUser.profileWork.jobCompany

                            else -> stringResource(id = R.string.title_not_set)
                        }
                    },
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
            Spacer(modifier = Modifier.height(MaterialTheme.dimens.size16))

            Divider(
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        Spacer(modifier = Modifier.height(MaterialTheme.dimens.size16))

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MaterialTheme.dimens.size8),
            text = stringResource(id = R.string.profile_edit_option_location),
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = MaterialTheme.dimens.font14
        )
        Spacer(modifier = Modifier.height(MaterialTheme.dimens.size16))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MaterialTheme.dimens.size8),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_edit_location),
                contentDescription = stringResource(id = R.string.profile_edit_option_location)
            )
            Spacer(modifier = Modifier.width(MaterialTheme.dimens.size8))
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = MaterialTheme.dimens.size8),
                text = state.previewUser.location.display(),
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDatingProfileView() {
    DatingAppTheme {
        DatingProfileView()
    }
}

@Preview(showBackground = true)
@Composable
fun DarkPreviewDatingProfileView() {
    DatingAppTheme(darkTheme = true) {
        DatingProfileView()
    }
}