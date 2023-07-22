/*
 * Create by Medvediev Viktor
 * last update: 10.07.23, 11:11
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.components.selectors.image

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.mvproject.datingapp.R
import com.mvproject.datingapp.ui.theme.DatingAppTheme
import com.mvproject.datingapp.ui.theme.dimens
import com.mvproject.datingapp.utils.STRING_EMPTY

@Composable
fun EditImageGridView(
    modifier: Modifier = Modifier,
    isCurrent: Boolean = false,
    initial: String = STRING_EMPTY,
    onClick: (String) -> Unit = {},
) {
    Card(
        modifier = modifier
            .height(MaterialTheme.dimens.size180)
            .clip(MaterialTheme.shapes.extraSmall),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.surfaceTint)
                .border(
                    width = MaterialTheme.dimens.size1,
                    color = MaterialTheme.colorScheme.outline,
                    shape = MaterialTheme.shapes.medium
                )
        ) {
            if (initial.isNotEmpty()) {
                AsyncImage(
                    model = initial,
                    contentDescription = null,
                    modifier = modifier
                        .clickable {
                            onClick(initial)
                        }
                        .fillMaxSize()
                        .align(Alignment.Center),
                    contentScale = ContentScale.Crop
                )
            } else {
                Image(
                    modifier = modifier
                        .clickable {
                            onClick(initial)
                        }
                        .fillMaxSize()
                        .align(Alignment.Center),
                    contentScale = ContentScale.Inside,
                    painter = painterResource(id = R.drawable.ic_logo_image),
                    contentDescription = "Back",
                )
            }

            if (isCurrent) {
                Image(
                    modifier = Modifier
                        .padding(
                            start = MaterialTheme.dimens.size5,
                            top = MaterialTheme.dimens.size5
                        )
                        .align(Alignment.TopStart),
                    painter = painterResource(id = R.drawable.ic_current_profile_image),
                    contentDescription = initial
                )
            }

            IconButton(
                onClick = {
                    onClick(initial)
                },
                modifier = Modifier
                    .align(Alignment.BottomEnd),
                colors = IconButtonDefaults.filledIconButtonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color.Transparent
                )
            ) {
                val icon = if (initial.isNotEmpty())
                    R.drawable.ic_edit_profile_image
                else
                    R.drawable.ic_add_profile_image

                Image(
                    painter = painterResource(id = icon),
                    contentDescription = "Back",
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewEditImageGridView() {
    DatingAppTheme {
        EditImageGridView(

        )
    }
}

@Preview(showBackground = true)
@Composable
fun DarkPreviewEditImageGridView() {
    DatingAppTheme(darkTheme = true) {
        EditImageGridView(

        )
    }
}