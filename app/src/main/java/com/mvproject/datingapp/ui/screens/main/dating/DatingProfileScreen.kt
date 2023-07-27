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
        val pages = state.profile.photos

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
                        Image(
                            modifier = Modifier.fillMaxSize(),
                            painter = painterResource(id = pages[position]),
                            contentDescription = null,
                            contentScale = ContentScale.FillWidth
                        )
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
            profileName = state.profile.name,
            profileAge = calculatAgeMillis(state.profile.birthdate),
            profileInterest = state.profile.interest
        )

        Spacer(modifier = Modifier.height(MaterialTheme.dimens.size16))

        Divider(
            color = MaterialTheme.colorScheme.onSurface
        )

        if (state.profile.profileAbout.isNotEmpty()) {
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
                text = state.profile.profileAbout,
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
            if (state.profile.profileOrientation.isValueSet) {
                ChipInfo(
                    text = stringResource(id = state.profile.profileOrientation.title),
                    logo = painterResource(id = state.profile.profileOrientation.logo)
                )
            }

            if (state.profile.profileMarital.isValueSet) {
                ChipInfo(
                    text = stringResource(id = state.profile.profileMarital.title),
                    logo = painterResource(id = state.profile.profileMarital.logo)
                )
            }

            if (!state.profile.profileHeight.isHeightNotVisible) {
                ChipInfo(
                    text = stringResource(
                        id = R.string.profile_edit_option_height_data,
                        state.profile.profileHeight.height
                    ),
                    logo = painterResource(id = R.drawable.ic_edit_height)
                )
            }

            if (state.profile.profileChildren.isValueSet) {
                ChipInfo(
                    text = stringResource(id = state.profile.profileChildren.title),
                    logo = painterResource(id = state.profile.profileChildren.logo)
                )
            }

            if (state.profile.profileZodiac.isValueSet) {
                ChipInfo(
                    text = stringResource(id = state.profile.profileZodiac.title),
                    logo = painterResource(id = state.profile.profileZodiac.logo)
                )
            }

            if (state.profile.profileAlcohol.isValueSet) {
                ChipInfo(
                    text = stringResource(id = state.profile.profileAlcohol.title),
                    logo = painterResource(id = state.profile.profileAlcohol.logo)
                )
            }

            if (state.profile.profileSmoke.isValueSet) {
                ChipInfo(
                    text = stringResource(id = state.profile.profileSmoke.title),
                    logo = painterResource(id = state.profile.profileSmoke.logo)
                )
            }

            if (state.profile.profilePsyOrientation.isValueSet) {
                ChipInfo(
                    text = stringResource(id = state.profile.profilePsyOrientation.title),
                    logo = painterResource(id = state.profile.profilePsyOrientation.logo)
                )
            }

            if (state.profile.profileReligion.isValueSet) {
                ChipInfo(
                    text = stringResource(id = state.profile.profileReligion.title),
                    logo = painterResource(id = state.profile.profileReligion.logo)
                )
            }

            state.profile.profileLanguages.forEach { lang ->
                ChipInfo(
                    text = stringResource(id = lang.title),
                    logo = painterResource(id = lang.logo)
                )
            }

            state.profile.profilePets.forEach { pet ->
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

        if (!state.profile.profileWork.isBothEmpty) {
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
                    text = if (state.profile.profileWork.isBothFilled) {
                        stringResource(
                            id = R.string.profile_edit_option_work_data,
                            state.profile.profileWork.jobTitle,
                            state.profile.profileWork.jobCompany
                        )
                    } else {
                        when {
                            state.profile.profileWork.jobTitle.isNotEmpty() ->
                                state.profile.profileWork.jobTitle

                            state.profile.profileWork.jobCompany.isNotEmpty() ->
                                state.profile.profileWork.jobCompany

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
                text = state.profileLocation.display(),
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