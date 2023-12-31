/*
 * Create by Medvediev Viktor
 * last update: 05.07.23, 16:23
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.components.menuoptions

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.mvproject.datingapp.R
import com.mvproject.datingapp.ui.theme.DatingAppTheme
import com.mvproject.datingapp.ui.theme.dimens
import com.mvproject.datingapp.utils.STRING_EMPTY
import com.mvproject.datingapp.utils.WEIGHT_1

@Composable
fun OptionEditWithLogo(
    modifier: Modifier = Modifier,
    title: String = STRING_EMPTY,
    selected: String = STRING_EMPTY,
    logo: Painter? = null,
    onChange: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
            .clickable {
                onChange()
            }
            .padding(
                vertical = MaterialTheme.dimens.size8,
                horizontal = MaterialTheme.dimens.size16
            )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            logo?.let { img ->
                Image(
                    painter = img,
                    contentDescription = title
                )
            }

            Spacer(modifier = Modifier.width(MaterialTheme.dimens.size8))

            Text(
                modifier = Modifier.wrapContentWidth(),
                text = title,
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onPrimary
            )

            Spacer(modifier = Modifier.width(MaterialTheme.dimens.size8))

            Text(
                modifier = Modifier.weight(WEIGHT_1),
                text = selected,
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.inverseOnSurface.copy(alpha = 0.6f),
                textAlign = TextAlign.End
            )

            Spacer(modifier = Modifier.width(MaterialTheme.dimens.size10))

            Icon(
                painter = painterResource(id = R.drawable.ic_right),
                contentDescription = title,
                tint = MaterialTheme.colorScheme.inverseOnSurface.copy(alpha = 0.6f)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewOptionEditWithLogo() {
    DatingAppTheme {
        OptionEditWithLogo(
            logo = painterResource(id = R.drawable.ic_edit_marital),
            title = "Title",
            selected = "Not Set"
        )
    }
}