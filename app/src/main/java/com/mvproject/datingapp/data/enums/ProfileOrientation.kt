/*
 * Create by Medvediev Viktor
 * last update: 15.06.23, 13:04
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.data.enums

import androidx.annotation.StringRes
import com.mvproject.datingapp.R

enum class ProfileOrientation(
    @StringRes val title: Int
) {
    ORIENTATION_HETERO(
        title = R.string.option_orientation_hetero
    ),
    ORIENTATION_GAY(
        title = R.string.option_orientation_gay
    ),
    ORIENTATION_LESBIAN(
        title = R.string.option_orientation_lesbian
    ),
    ORIENTATION_BISEXUAL(
        title = R.string.option_orientation_bisexual
    ),
    ORIENTATION_ASEXUAL(
        title = R.string.option_orientation_asexual
    ),
    ORIENTATION_DEMISEXUAL(
        title = R.string.option_orientation_demisexual
    ),
    ORIENTATION_PANSEXUAL(
        title = R.string.option_orientation_pansexual
    ),
    ORIENTATION_QUEER(
        title = R.string.option_orientation_queer
    ),
    ORIENTATION_SEARCH_MYSELF(
        title = R.string.option_orientation_search_myself
    ),
    ORIENTATION_NOT_SET(
        title = R.string.option_not_set
    )
}