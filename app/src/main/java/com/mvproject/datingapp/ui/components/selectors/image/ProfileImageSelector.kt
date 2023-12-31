/*
 * Create by Medvediev Viktor
 * last update: 10.07.23, 11:11
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.components.selectors.image

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.mvproject.datingapp.R
import com.mvproject.datingapp.ui.components.buttons.GradientButton
import com.mvproject.datingapp.ui.theme.DatingAppTheme
import com.mvproject.datingapp.ui.theme.dimens
import com.mvproject.datingapp.utils.PHOTO_COLUMNS_COUNT
import com.mvproject.datingapp.utils.PHOTO_MIN_COUNT
import com.mvproject.datingapp.utils.STRING_EMPTY
import com.mvproject.datingapp.utils.WEIGHT_1
import com.mvproject.datingapp.utils.prepareImages

@Composable
fun ProfileImageSelector(
    modifier: Modifier = Modifier,
    title: String = stringResource(id = R.string.scr_auth_photo_select_title),
    description: String = stringResource(id = R.string.scr_auth_photo_select_description),
    onPhotoSelected: (List<String>) -> Unit = {}
) {
    val listUri = remember {
        mutableStateListOf<String>().also {
            it.addAll(prepareImages())
        }
    }

    val isCanComplete by remember {
        derivedStateOf {
            listUri.filter { it != STRING_EMPTY }.size >= PHOTO_MIN_COUNT
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(
                vertical = MaterialTheme.dimens.size12,
                horizontal = MaterialTheme.dimens.size24
            ),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = title,
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.displayLarge
        )

        Spacer(modifier = Modifier.height(MaterialTheme.dimens.size16))

        LazyVerticalGrid(
            columns = GridCells.Fixed(PHOTO_COLUMNS_COUNT),
            state = rememberLazyGridState(),
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.dimens.size8),
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.dimens.size8),
            contentPadding = PaddingValues(MaterialTheme.dimens.size2),
            content = {
                items(listUri.size) { index ->
                    ImageGridView(
                        modifier = Modifier
                            .fillMaxSize(),
                        onImageSelect = { uri ->
                            listUri[index] = uri
                        },
                        onImageRemove = { _ ->
                            listUri[index] = STRING_EMPTY
                        }
                    )
                }
            }
        )

        Spacer(modifier = Modifier.height(MaterialTheme.dimens.size16))

        Text(
            modifier = Modifier
                .padding(horizontal = MaterialTheme.dimens.size24),
            text = description,
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.labelMedium,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.weight(WEIGHT_1))

        val textBtn = if (isCanComplete)
            stringResource(id = R.string.btn_title_continue)
        else
            stringResource(id = R.string.btn_title_select_photo)

        GradientButton(
            modifier = Modifier.fillMaxWidth(),
            title = textBtn,
            onClick = {
                if (isCanComplete) {
                    val uriList = listUri.toList()
                    onPhotoSelected(uriList)
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewProfileImageSelector() {
    DatingAppTheme {
        ProfileImageSelector(

        )
    }
}