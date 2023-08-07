/*
 * Create by Medvediev Viktor
 * last update: 05.07.23, 16:23
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.components.menuoptions

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.mvproject.datingapp.R
import com.mvproject.datingapp.data.enums.ProfileInterest
import com.mvproject.datingapp.ui.components.info.InterestInfo
import com.mvproject.datingapp.ui.theme.DatingAppTheme
import com.mvproject.datingapp.ui.theme.dimens
import com.mvproject.datingapp.utils.STRING_EMPTY
import com.mvproject.datingapp.utils.WEIGHT_1

@Composable
fun OptionEditInterest(
    modifier: Modifier = Modifier,
    title: String = STRING_EMPTY,
    selectedInterest: ProfileInterest,
    onChange: () -> Unit = {}
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.primary)
            .clickable {
                onChange()
            }
            .padding(MaterialTheme.dimens.size16),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(WEIGHT_1)) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = title,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = MaterialTheme.dimens.font14
            )
            Spacer(modifier = Modifier.height(MaterialTheme.dimens.size16))

            InterestInfo(
                modifier = Modifier.wrapContentWidth(),
                selectedInterest = selectedInterest
            )
        }

        Spacer(modifier = Modifier.width(MaterialTheme.dimens.size16))

        Icon(
            painter = painterResource(id = R.drawable.ic_right),
            contentDescription = title,
            tint = MaterialTheme.colorScheme.inverseOnSurface.copy(alpha = 0.6f)
        )

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewOptionEditInterest() {
    DatingAppTheme {
        OptionEditInterest(
            title = "What do I want?",
            selectedInterest = ProfileInterest.INTEREST_DATE
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DarkPreviewOptionEditInterest() {
    DatingAppTheme(darkTheme = true) {
        OptionEditInterest(
            title = "What do I want?",
            selectedInterest = ProfileInterest.INTEREST_RELATION
        )
    }
}