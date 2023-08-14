/*
 * Create by Medvediev Viktor
 * last update: 08.08.23, 17:32
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.components.composable.view

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.mvproject.datingapp.R
import com.mvproject.datingapp.ui.theme.DatingAppTheme
import com.mvproject.datingapp.ui.theme.dimens
import com.mvproject.datingapp.utils.STRING_EMPTY
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.transformation.blur.BlurTransformationPlugin

@Composable
fun CurrentLikesView(
    profileLogo: String = STRING_EMPTY,
    profileLikes: Int = 99,
    onLikesClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .width(MaterialTheme.dimens.size74)
            .height(MaterialTheme.dimens.size88)
            .clickable {
                onLikesClick()
            }
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(MaterialTheme.dimens.size64)
            ) {

                CoilImage(
                    imageModel = { Uri.parse(profileLogo) },
                    modifier = Modifier
                        .size(MaterialTheme.dimens.size60)
                        .border(
                            MaterialTheme.dimens.size2,
                            color = MaterialTheme.colorScheme.secondary,
                            shape = CircleShape
                        )
                        .padding(MaterialTheme.dimens.size4)
                        .clip(CircleShape)
                        .align(Alignment.TopCenter),
                    imageOptions = ImageOptions(
                        contentScale = ContentScale.Crop,
                        alignment = Alignment.Center
                    ),
                    component = rememberImageComponent {
                        +BlurTransformationPlugin(radius = 10)
                    }
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
                    text = "+$profileLikes",
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.labelSmall,
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.height(MaterialTheme.dimens.size4))

            Text(
                text = stringResource(id = R.string.current_likes_title),
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.titleSmall,
                fontSize = MaterialTheme.dimens.font12
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCurrentLikesView() {
    DatingAppTheme {
        CurrentLikesView()
    }
}