/*
 * Create by Medvediev Viktor
 * last update: 11.08.23, 16:04
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.components.composable.candidate

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.mvproject.datingapp.R
import com.mvproject.datingapp.data.dummy.MatchUser
import com.mvproject.datingapp.ui.components.composable.view.ChipInfo
import com.mvproject.datingapp.ui.components.info.ShortProfileInfo
import com.mvproject.datingapp.ui.theme.dimens
import com.mvproject.datingapp.utils.calculatAgeMillis

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CandidateCharacteristicsView(
    previewUser: MatchUser
) {
    Column {
        Spacer(modifier = Modifier.height(MaterialTheme.dimens.size16))

        ShortProfileInfo(
            modifier = Modifier
                .padding(horizontal = MaterialTheme.dimens.size8),
            profileName = previewUser.name,
            profileAge = calculatAgeMillis(previewUser.birthdate),
            profileInterest = previewUser.interest
        )

        Spacer(modifier = Modifier.height(MaterialTheme.dimens.size16))

        Divider(
            color = MaterialTheme.colorScheme.onSurface
        )

        if (previewUser.profileAbout.isNotEmpty()) {
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
                text = previewUser.profileAbout,
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
            if (previewUser.profileOrientation.isValueSet) {
                ChipInfo(
                    text = stringResource(id = previewUser.profileOrientation.title),
                    logo = painterResource(id = previewUser.profileOrientation.logo)
                )
            }

            if (previewUser.profileMarital.isValueSet) {
                ChipInfo(
                    text = stringResource(id = previewUser.profileMarital.title),
                    logo = painterResource(id = previewUser.profileMarital.logo)
                )
            }

            if (!previewUser.profileHeight.isHeightNotVisible) {
                ChipInfo(
                    text = stringResource(
                        id = R.string.profile_edit_option_height_data,
                        previewUser.profileHeight.height
                    ),
                    logo = painterResource(id = R.drawable.ic_edit_height)
                )
            }

            if (previewUser.profileChildren.isValueSet) {
                ChipInfo(
                    text = stringResource(id = previewUser.profileChildren.title),
                    logo = painterResource(id = previewUser.profileChildren.logo)
                )
            }

            if (previewUser.profileZodiac.isValueSet) {
                ChipInfo(
                    text = stringResource(id = previewUser.profileZodiac.title),
                    logo = painterResource(id = previewUser.profileZodiac.logo)
                )
            }

            if (previewUser.profileAlcohol.isValueSet) {
                ChipInfo(
                    text = stringResource(id = previewUser.profileAlcohol.title),
                    logo = painterResource(id = previewUser.profileAlcohol.logo)
                )
            }

            if (previewUser.profileSmoke.isValueSet) {
                ChipInfo(
                    text = stringResource(id = previewUser.profileSmoke.title),
                    logo = painterResource(id = previewUser.profileSmoke.logo)
                )
            }

            if (previewUser.profilePsyOrientation.isValueSet) {
                ChipInfo(
                    text = stringResource(id = previewUser.profilePsyOrientation.title),
                    logo = painterResource(id = previewUser.profilePsyOrientation.logo)
                )
            }

            if (previewUser.profileReligion.isValueSet) {
                ChipInfo(
                    text = stringResource(id = previewUser.profileReligion.title),
                    logo = painterResource(id = previewUser.profileReligion.logo)
                )
            }

            previewUser.profileLanguages.forEach { lang ->
                ChipInfo(
                    text = stringResource(id = lang.title),
                    logo = painterResource(id = lang.logo)
                )
            }

            previewUser.profilePets.forEach { pet ->
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

        if (!previewUser.profileWork.isBothEmpty) {
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
                    text = if (previewUser.profileWork.isBothFilled) {
                        stringResource(
                            id = R.string.profile_edit_option_work_data,
                            previewUser.profileWork.jobTitle,
                            previewUser.profileWork.jobCompany
                        )
                    } else {
                        when {
                            previewUser.profileWork.jobTitle.isNotEmpty() ->
                                previewUser.profileWork.jobTitle

                            previewUser.profileWork.jobCompany.isNotEmpty() ->
                                previewUser.profileWork.jobCompany

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
                text = previewUser.location.display(),
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}