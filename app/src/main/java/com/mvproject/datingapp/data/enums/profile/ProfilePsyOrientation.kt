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

enum class ProfilePsyOrientation(
    @StringRes val title: Int,
    @DrawableRes val logo: Int = R.drawable.ic_edit_character
) {
    PSY_ORIENTATION_INTROVERT(
        title = R.string.option_psy_orientation_introvert
    ),
    PSY_ORIENTATION_EXTRAVERT(
        title = R.string.option_psy_orientation_extravert
    ),
    PSY_ORIENTATION_BETWEEN(
        title = R.string.option_psy_orientation_between
    ),
    PSY_ORIENTATION_NOT_SET(
        title = R.string.option_not_set
    );

    fun display() = if (this == PSY_ORIENTATION_NOT_SET) R.string.title_not_set else title

    val isValueSet get() = this != PSY_ORIENTATION_NOT_SET

    companion object {
        fun fromStringOrDefault(str: String?): ProfilePsyOrientation {
            if (str == null) return PSY_ORIENTATION_BETWEEN
            return try {
                ProfilePsyOrientation.valueOf(str)
            } catch (ex: Exception) {
                PSY_ORIENTATION_BETWEEN
            }
        }
    }
}