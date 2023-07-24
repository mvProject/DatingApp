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

enum class ProfileSmoke(
    @StringRes val title: Int
) {
    SMOKE_YES(
        title = R.string.option_smoke_yes
    ),
    SMOKE_NO(
        title = R.string.option_smoke_no
    ),
    SMOKE_SOMETIMES(
        title = R.string.option_smoke_sometimes
    ),
    SMOKE_NOT_SET(
        title = R.string.option_not_set
    );
    fun display() = if (this == SMOKE_NOT_SET) R.string.title_not_set else title

    companion object {
        fun fromStringOrDefault(str: String?): ProfileSmoke {
            if (str == null) return SMOKE_SOMETIMES
            return try {
                ProfileSmoke.valueOf(str)
            } catch (ex: Exception) {
                SMOKE_SOMETIMES
            }
        }
    }
}