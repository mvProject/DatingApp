/*
 * Create by Medvediev Viktor
 * last update: 27.07.23, 13:23
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.data.enums.profile

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.mvproject.datingapp.R

enum class ProfileAlcohol(
    @StringRes val title: Int,
    @DrawableRes val logo: Int = R.drawable.ic_edit_alcohol
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
    );

    fun display() = if (this == ALCOHOL_NOT_SET) R.string.title_not_set else title

    val isValueSet get() = this != ALCOHOL_NOT_SET

    companion object {
        fun fromStringOrDefault(str: String?): ProfileAlcohol {
            if (str == null) return ALCOHOL_OFTEN
            return try {
                ProfileAlcohol.valueOf(str)
            } catch (ex: Exception) {
                ALCOHOL_OFTEN
            }
        }
    }
}