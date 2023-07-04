/*
 * Create by Medvediev Viktor
 * last update: 09.06.23, 16:39
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.mvproject.datingapp.R
import com.mvproject.datingapp.data.enums.ProfileGender
import com.mvproject.datingapp.ui.components.buttons.GradientButton
import com.mvproject.datingapp.ui.theme.DatingAppTheme
import com.mvproject.datingapp.ui.theme.dimens
import com.mvproject.datingapp.utils.WEIGHT_1
import com.mvproject.datingapp.utils.borderColor
import com.mvproject.datingapp.utils.textColor

@Composable
fun GenderSelector(
    modifier: Modifier = Modifier,
    title: String = stringResource(id = R.string.scr_auth_gender_title),
    titleMan: String = stringResource(id = R.string.scr_auth_gender_man),
    titleWoman: String = stringResource(id = R.string.scr_auth_gender_woman),
    onGenderSelect: (ProfileGender) -> Unit = {}
) {

    var selected by remember {
        mutableStateOf(ProfileGender.MALE)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(
                vertical = MaterialTheme.dimens.size12,
                horizontal = MaterialTheme.dimens.size16
            ),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.displayLarge
        )

        Spacer(modifier = Modifier.height(MaterialTheme.dimens.size24))

        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Card(
                modifier = Modifier
                    .clickable {
                        selected = ProfileGender.FEMALE
                    }
                    .height(MaterialTheme.dimens.size130)
                    .width(MaterialTheme.dimens.size156)
                    //  .background(color = backColor(selected == ProfileGender.FEMALE))
                    .border(
                        width = MaterialTheme.dimens.size1,
                        color = borderColor(selected == ProfileGender.FEMALE),
                        shape = MaterialTheme.shapes.medium
                    )
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        modifier = Modifier
                            .size(MaterialTheme.dimens.size40),
                        painter = painterResource(id = R.drawable.ic_gender_woman),
                        contentDescription = titleWoman,
                        tint = textColor(selected == ProfileGender.FEMALE)
                    )

                    Spacer(modifier = Modifier.height(MaterialTheme.dimens.size16))

                    Text(
                        text = titleWoman,
                        color = textColor(selected == ProfileGender.FEMALE),
                        style = MaterialTheme.typography.labelLarge
                    )
                }
            }

            Card(
                modifier = Modifier
                    .clickable {
                        selected = ProfileGender.MALE
                    }
                    .height(MaterialTheme.dimens.size130)
                    .width(MaterialTheme.dimens.size156)
                    //  .background(color = backColor(selected == ProfileGender.MALE))
                    .border(
                        width = MaterialTheme.dimens.size1,
                        color = borderColor(selected == ProfileGender.MALE),
                        shape = MaterialTheme.shapes.medium
                    )
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        modifier = Modifier
                            .size(MaterialTheme.dimens.size40),
                        painter = painterResource(id = R.drawable.ic_gender_man),
                        contentDescription = titleMan,
                        tint = textColor(selected == ProfileGender.MALE)
                    )

                    Spacer(modifier = Modifier.height(MaterialTheme.dimens.size16))

                    Text(
                        text = titleMan,
                        color = textColor(selected == ProfileGender.MALE),
                        style = MaterialTheme.typography.labelLarge
                    )
                }
            }
        }

        Spacer(modifier = Modifier.weight(WEIGHT_1))

        GradientButton(
            modifier = Modifier.fillMaxWidth(),
            title = stringResource(id = R.string.btn_title_continue),
            onClick = {
                onGenderSelect(selected)
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCodeGenderSelector() {
    DatingAppTheme {
        GenderSelector()
    }
}

@Preview(showBackground = true)
@Composable
fun DarkPreviewGenderSelector() {
    DatingAppTheme(darkTheme = true) {
        GenderSelector()
    }
}