/*
 * Create by Medvediev Viktor
 * last update: 15.06.23, 13:04
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.data.enums

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.mvproject.datingapp.R

enum class ProfileChildren(
    @StringRes val title: Int,
    @DrawableRes val logo: Int = R.drawable.ic_edit_children
) {
    CHILDREN_WANT_SOMETIME(
        title = R.string.option_children_sometime
    ),
    CHILDREN_WANT_SOON(
        title = R.string.option_children_soon
    ),
    CHILDREN_NO_WANT(
        title = R.string.option_children_no_want_children
    ),
    CHILDREN_HAVE(
        title = R.string.option_children_have_children
    ),
    CHILDREN_NOT_SET(
        title = R.string.option_not_set
    );

    fun display() = if (this == CHILDREN_NOT_SET) R.string.title_not_set else title

    val isValueSet get() = this != CHILDREN_NOT_SET

    companion object {
        fun fromStringOrDefault(str: String?): ProfileChildren {
            if (str == null) return CHILDREN_NO_WANT
            return try {
                ProfileChildren.valueOf(str)
            } catch (ex: Exception) {
                CHILDREN_NO_WANT
            }
        }
    }
}