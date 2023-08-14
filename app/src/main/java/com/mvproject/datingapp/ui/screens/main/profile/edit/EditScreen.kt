/*
 * Create by Medvediev Viktor
 * last update: 18.07.23, 18:54
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.main.profile.edit

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mvproject.datingapp.R
import com.mvproject.datingapp.ui.components.buttons.MenuButton
import com.mvproject.datingapp.ui.components.composable.view.StaticGrid
import com.mvproject.datingapp.ui.components.dialog.BottomDialog
import com.mvproject.datingapp.ui.components.menuoptions.OptionEditInterest
import com.mvproject.datingapp.ui.components.menuoptions.OptionEditWithLogo
import com.mvproject.datingapp.ui.components.menuoptions.OptionEditWithTitle
import com.mvproject.datingapp.ui.components.selectors.image.EditImageGridView
import com.mvproject.datingapp.ui.screens.main.profile.edit.action.EditPhotoAction
import com.mvproject.datingapp.ui.screens.main.profile.edit.state.EditDataState
import com.mvproject.datingapp.ui.screens.main.profile.edit.state.EditProfileOption
import com.mvproject.datingapp.ui.theme.DatingAppTheme
import com.mvproject.datingapp.ui.theme.dimens
import com.mvproject.datingapp.utils.INT_ZERO
import com.mvproject.datingapp.utils.PHOTO_COLUMNS_COUNT
import com.mvproject.datingapp.utils.STRING_COMA_SPACE
import com.mvproject.datingapp.utils.notNullOrEmpty
import timber.log.Timber

@Composable
fun EditScreen(
    viewModel: EditViewModel,
    onNavigationBack: () -> Unit = {},
    onNavigationPreview: () -> Unit = {},
    onNavigationChange: (String) -> Unit = {}
) {
    val profileState by viewModel.profileState.collectAsStateWithLifecycle()
    val userPhotos = viewModel.userPhotos

    EditView(
        state = profileState,
        userPhotos = userPhotos,
        onPhotoAction = viewModel::processAction,
        onChangeClick = onNavigationChange,
        onPreviewClick = onNavigationPreview,
        onBackClick = onNavigationBack
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditView(
    state: EditDataState = EditDataState(),
    userPhotos: List<String> = emptyList(),
    onPhotoAction: (EditPhotoAction) -> Unit = {},
    onChangeClick: (String) -> Unit = {},
    onPreviewClick: () -> Unit = {},
    onBackClick: () -> Unit = {}
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.screen_title_profile_edit),
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.headlineLarge,
                    )
                },
                actions = {
                    Text(
                        modifier = Modifier.clickable {
                            onPreviewClick()
                        },
                        text = stringResource(id = R.string.screen_title_profile_preview),
                        color = MaterialTheme.colorScheme.secondary,
                        style = MaterialTheme.typography.labelLarge,
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = onBackClick
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_navigate_back),
                            contentDescription = "Back",
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }
                }
            )
        },
        containerColor = MaterialTheme.colorScheme.surfaceVariant
    ) { paddingValues ->
        val isPhotoOptionOpen = remember { mutableStateOf(false) }
        var photoSelectedIndex by remember { mutableIntStateOf(INT_ZERO) }

        val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.PickVisualMedia(),
            onResult = { uri ->
                if (uri.notNullOrEmpty()) {
                    onPhotoAction(EditPhotoAction.UploadPhoto(photoSelectedIndex, uri.toString()))
                } else {
                    Timber.e("testing uri empty")
                }
            }
        )

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(vertical = MaterialTheme.dimens.size8)
        ) {
            StaticGrid(
                columns = PHOTO_COLUMNS_COUNT,
                itemCount = userPhotos.size,
            ) { index ->
                EditImageGridView(
                    initial = userPhotos[index],
                    isCurrent = userPhotos[index] == state.currentProfile.profilePictureUrl,
                    modifier = Modifier.fillMaxSize(),
                    onClick = {
                        if (!isPhotoOptionOpen.value) {
                            photoSelectedIndex = index
                            isPhotoOptionOpen.value = true
                        }
                    }
                )
            }

            OptionsView(
                state = state,
                onChangeClick = onChangeClick
            )
        }

        BottomDialog(
            modifier = Modifier
                .padding(paddingValues),
            isVisible = isPhotoOptionOpen
        ) {
            Column()
            {
                MenuButton(
                    modifier = Modifier.fillMaxWidth(),
                    title = stringResource(id = R.string.btn_title_set_photo),
                    btnColor = MaterialTheme.colorScheme.surfaceVariant,
                    titleColor = MaterialTheme.colorScheme.onPrimary,
                    isEnabled = userPhotos[photoSelectedIndex].isNotEmpty(),
                    onClick = {
                        onPhotoAction(EditPhotoAction.SetPhotoAsDefault(photoSelectedIndex))
                        isPhotoOptionOpen.value = false
                    }
                )
                Divider(
                    color = MaterialTheme.colorScheme.outline
                )
                MenuButton(
                    modifier = Modifier.fillMaxWidth(),
                    title = stringResource(id = R.string.btn_title_upload_photo),
                    btnColor = MaterialTheme.colorScheme.surfaceVariant,
                    titleColor = MaterialTheme.colorScheme.onPrimary,
                    onClick = {
                        singlePhotoPickerLauncher.launch(
                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                        )
                        isPhotoOptionOpen.value = false
                    }
                )
                Divider(
                    color = MaterialTheme.colorScheme.outline
                )
                MenuButton(
                    modifier = Modifier.fillMaxWidth(),
                    title = stringResource(id = R.string.btn_title_delete),
                    btnColor = MaterialTheme.colorScheme.surfaceVariant,
                    titleColor = MaterialTheme.colorScheme.onError,
                    isEnabled = userPhotos[photoSelectedIndex].isNotEmpty(),
                    onClick = {
                        onPhotoAction(EditPhotoAction.RemovePhoto(photoSelectedIndex))
                        photoSelectedIndex = INT_ZERO
                        isPhotoOptionOpen.value = false
                    }
                )
                MenuButton(
                    modifier = Modifier.fillMaxWidth(),
                    title = stringResource(id = R.string.btn_title_cancel),
                    btnColor = MaterialTheme.colorScheme.primary,
                    titleColor = MaterialTheme.colorScheme.onPrimary,
                    isBold = true,
                    onClick = {
                        photoSelectedIndex = INT_ZERO
                        isPhotoOptionOpen.value = false
                    }
                )
            }
        }
    }
}

@Composable
private fun OptionsView(
    state: EditDataState = EditDataState(),
    onChangeClick: (String) -> Unit = {},
) {

    val isWorkBothFilled by remember {
        derivedStateOf {
            state.currentProfile.profileWork.jobTitle.isNotEmpty() &&
                    state.currentProfile.profileWork.jobCompany.isNotEmpty()
        }
    }

    Column() {
        OptionEditInterest(
            title = stringResource(id = R.string.profile_edit_option_what_do_i_want),
            selectedInterest = state.profileInterest,
            onChange = { onChangeClick(EditProfileOption.INTEREST.name) }
        )

        Spacer(modifier = Modifier.height(MaterialTheme.dimens.size16))

        OptionEditWithTitle(
            title = stringResource(id = R.string.profile_edit_option_about_me),
            selected = state.currentProfile.profileAbout.ifEmpty {
                stringResource(id = R.string.title_not_set)
            },
            onChange = { onChangeClick(EditProfileOption.ABOUT.name) }
        )

        Spacer(modifier = Modifier.height(MaterialTheme.dimens.size16))

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.primary)
                .padding(MaterialTheme.dimens.size16),
            text = stringResource(id = R.string.profile_edit_option_basic_information),
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = MaterialTheme.dimens.font14
        )

        OptionEditWithLogo(
            logo = painterResource(id = R.drawable.ic_edit_orientation),
            title = stringResource(id = R.string.profile_edit_option_sexual_orientation),
            selected = stringResource(id = state.currentProfile.profileOrientation.display()),
            onChange = { onChangeClick(EditProfileOption.ORIENTATION.name) }
        )

        OptionEditWithLogo(
            logo = painterResource(id = R.drawable.ic_edit_marital),
            title = stringResource(id = R.string.profile_edit_option_marital_status),
            selected = stringResource(id = state.currentProfile.profileMarital.display()),
            onChange = { onChangeClick(EditProfileOption.MARITAL_STATUS.name) }
        )
        OptionEditWithLogo(
            logo = painterResource(id = R.drawable.ic_edit_children),
            title = stringResource(id = R.string.profile_edit_option_children),
            selected = stringResource(id = state.currentProfile.profileChildren.display()),
            onChange = { onChangeClick(EditProfileOption.CHILDREN.name) }
        )
        OptionEditWithLogo(
            logo = painterResource(id = R.drawable.ic_edit_height),
            title = stringResource(id = R.string.profile_edit_option_height),
            selected = stringResource(
                id = R.string.profile_edit_option_height_data,
                state.profileHeight
            ),
            onChange = { onChangeClick(EditProfileOption.HEIGHT.name) }
        )
        OptionEditWithLogo(
            logo = painterResource(id = R.drawable.ic_edit_zodiac),
            title = stringResource(id = R.string.profile_edit_option_zodiac_sign),
            selected = stringResource(id = state.currentProfile.profileZodiac.display()),
            onChange = { onChangeClick(EditProfileOption.ZODIAC.name) }
        )
        OptionEditWithLogo(
            logo = painterResource(id = R.drawable.ic_edit_alcohol),
            title = stringResource(id = R.string.profile_edit_option_alcohol),
            selected = stringResource(id = state.currentProfile.profileAlcohol.display()),
            onChange = { onChangeClick(EditProfileOption.ALCOHOL.name) }
        )
        OptionEditWithLogo(
            logo = painterResource(id = R.drawable.ic_edit_smoking),
            title = stringResource(id = R.string.profile_edit_option_smoking),
            selected = stringResource(id = state.currentProfile.profileSmoke.display()),
            onChange = { onChangeClick(EditProfileOption.SMOKE.name) }
        )
        OptionEditWithLogo(
            logo = painterResource(id = R.drawable.ic_edit_character),
            title = stringResource(id = R.string.profile_edit_option_character),
            selected = stringResource(id = state.currentProfile.profilePsyOrientation.display()),
            onChange = { onChangeClick(EditProfileOption.PSY_ORIENTATION.name) }
        )
        OptionEditWithLogo(
            logo = painterResource(id = R.drawable.ic_edit_religion),
            title = stringResource(id = R.string.profile_edit_option_religion),
            selected = stringResource(id = state.currentProfile.profileReligion.display()),
            onChange = { onChangeClick(EditProfileOption.RELIGION.name) }
        )

        val langs = state.currentProfile.profileLanguages.map {
            stringResource(id = it.title)
        }
        OptionEditWithLogo(
            logo = painterResource(id = R.drawable.ic_edit_languages),
            title = stringResource(id = R.string.profile_edit_option_languages),
            selected = langs.joinToString(STRING_COMA_SPACE).ifEmpty {
                stringResource(id = R.string.title_not_set)
            },
            onChange = { onChangeClick(EditProfileOption.LANGUAGES.name) }
        )

        val pets = state.currentProfile.profilePets.map {
            stringResource(id = it.title)
        }
        OptionEditWithLogo(
            logo = painterResource(id = R.drawable.ic_edit_pets),
            title = stringResource(id = R.string.profile_edit_option_pets),
            selected = pets.joinToString(STRING_COMA_SPACE).ifEmpty {
                stringResource(id = R.string.title_not_set)
            },
            onChange = { onChangeClick(EditProfileOption.PETS.name) }
        )

        Spacer(modifier = Modifier.height(MaterialTheme.dimens.size16))

        OptionEditWithTitle(
            title = stringResource(id = R.string.profile_edit_option_work),
            selected = if (isWorkBothFilled) {
                stringResource(
                    id = R.string.profile_edit_option_work_data,
                    state.currentProfile.profileWork.jobTitle,
                    state.currentProfile.profileWork.jobCompany
                )
            } else {
                when {
                    state.currentProfile.profileWork.jobTitle.isNotEmpty() ->
                        state.currentProfile.profileWork.jobTitle

                    state.currentProfile.profileWork.jobCompany.isNotEmpty() ->
                        state.currentProfile.profileWork.jobCompany

                    else -> stringResource(id = R.string.title_not_set)
                }
            },
            onChange = { onChangeClick(EditProfileOption.WORK.name) }
        )

        Spacer(modifier = Modifier.height(MaterialTheme.dimens.size16))

        OptionEditWithTitle(
            title = stringResource(id = R.string.profile_edit_option_location),
            selected = state.profileLocation.display(),
            onChange = { onChangeClick(EditProfileOption.LOCATION.name) }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewEditView() {
    DatingAppTheme {
        EditView()
    }
}

@Preview(showBackground = true)
@Composable
fun DarkPreviewEditView() {
    DatingAppTheme(darkTheme = true) {
        EditView()
    }
}