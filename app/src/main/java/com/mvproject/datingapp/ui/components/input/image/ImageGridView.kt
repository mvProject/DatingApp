/*
 * Create by Medvediev Viktor
 * last update: 15.06.23, 19:33
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.components.input.image

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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

@Composable
fun ImageGridView(
    modifier: Modifier = Modifier,
    onImageSelect: (String) -> Unit = {},
    onImageRemove: (String) -> Unit = {}
) {
    var selectedImageUri by remember {
        mutableStateOf<Uri?>(null)
    }

    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            if (selectedImageUri != null) {
                onImageRemove(selectedImageUri.toString())
            }
            selectedImageUri = uri
            onImageSelect(selectedImageUri.toString())
        }
    )

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
            if (selectedImageUri != null) {
                AsyncImage(
                    model = selectedImageUri,
                    contentDescription = null,
                    modifier = modifier
                        .clickable {
                            singlePhotoPickerLauncher.launch(
                                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                            )
                        }
                        .fillMaxSize()
                        .align(Alignment.Center),
                    contentScale = ContentScale.Crop
                )
            } else {
                Image(
                    modifier = modifier
                        .clickable {
                            singlePhotoPickerLauncher.launch(
                                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                            )
                        }
                        .fillMaxSize()
                        .align(Alignment.Center),
                    contentScale = ContentScale.Inside,
                    painter = painterResource(id = R.drawable.ic_logo_image),
                    contentDescription = "Back",
                )
            }

            IconButton(
                onClick = {
                    if (selectedImageUri != null) {
                        onImageRemove(selectedImageUri.toString())
                        selectedImageUri = null
                    } else {
                        singlePhotoPickerLauncher.launch(
                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                        )
                    }
                },
                modifier = Modifier
                    .align(Alignment.BottomEnd),
                colors = IconButtonDefaults.filledIconButtonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color.Transparent
                )
            ) {
                val icon = if (selectedImageUri != null)
                    painterResource(id = R.drawable.ic_remove_profile_image)
                else
                    painterResource(id = R.drawable.ic_add_profile_image)

                Image(
                    painter = icon,
                    contentDescription = "Back",
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewImageGridView() {
    DatingAppTheme {
        ImageGridView(

        )
    }
}

@Preview(showBackground = true)
@Composable
fun DarkPreviewImageGridView() {
    DatingAppTheme(darkTheme = true) {
        ImageGridView(

        )
    }
}