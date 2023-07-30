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

enum class ProfileOrientation(
    @StringRes val title: Int,
    @DrawableRes val logo: Int = R.drawable.ic_edit_orientation
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
    );

    fun display() = if (this == ORIENTATION_NOT_SET) R.string.title_not_set else title

    val isValueSet get() = this != ORIENTATION_NOT_SET

    companion object {
        fun fromStringOrDefault(str: String?): ProfileOrientation {
            if (str == null) return ORIENTATION_HETERO
            return try {
                ProfileOrientation.valueOf(str)
            } catch (ex: Exception) {
                ORIENTATION_HETERO
            }
        }
    }
}