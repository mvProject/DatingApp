/*
 * Create by Medvediev Viktor
 * last update: 09.06.23, 16:39
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.mvproject.datingapp.R
import com.mvproject.datingapp.data.ProfileGender
import com.mvproject.datingapp.ui.theme.DatingAppTheme
import com.mvproject.datingapp.ui.theme.dimens
import com.mvproject.datingapp.utils.backColor
import com.mvproject.datingapp.utils.borderColor
import com.mvproject.datingapp.utils.textColor
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun GenderSelector(
    modifier: Modifier = Modifier,
    title: String = "",
    titleMan: String = "",
    titleWoman: String = "",
    onGenderSelect: (ProfileGender) -> Unit = {}
) {

    var selected by remember {
        mutableStateOf<ProfileGender?>(null)
    }

    val scope = rememberCoroutineScope()

    Column(
        modifier = modifier,
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
                        scope.launch {
                            delay(500)
                            onGenderSelect(ProfileGender.FEMALE)
                        }
                    }
                    .height(MaterialTheme.dimens.size130)
                    .width(MaterialTheme.dimens.size156)
                    .background(color = backColor(selected == ProfileGender.FEMALE))
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
                        scope.launch {
                            delay(500)
                            onGenderSelect(ProfileGender.MALE)
                        }
                    }
                    .height(MaterialTheme.dimens.size130)
                    .width(MaterialTheme.dimens.size156)
                    .background(color = backColor(selected == ProfileGender.MALE))
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
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCodeGenderSelector() {
    DatingAppTheme {
        GenderSelector(
            title = stringResource(id = R.string.scr_auth_gender_title),
            titleMan = stringResource(id = R.string.scr_auth_gender_man),
            titleWoman = stringResource(id = R.string.scr_auth_gender_woman),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DarkPreviewGenderSelector() {
    DatingAppTheme(darkTheme = true) {
        GenderSelector(
            title = stringResource(id = R.string.scr_auth_gender_title),
            titleMan = stringResource(id = R.string.scr_auth_gender_man),
            titleWoman = stringResource(id = R.string.scr_auth_gender_woman),
        )
    }
}