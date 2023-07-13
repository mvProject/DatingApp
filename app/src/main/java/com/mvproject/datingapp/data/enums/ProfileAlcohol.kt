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

enum class ProfileAlcohol(
    @StringRes val title: Int
) {
    ALCOHOL_COMPANY(
        title = R.string.option_alcohol_company
    ),
    ALCOHOL_NEVER(
        title = R.string.option_alcohol_never
    ),
    ALCOHOL_OFTEN(
        title = R.string.option_alcohol_often
    ),
    ALCOHOL_ABSTAIN(
        title = R.string.option_alcohol_abstain
    ),
    ALCOHOL_NOT_SET(
        title = R.string.option_not_set
    )
}