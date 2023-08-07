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

enum class ProfileReligion(
    @StringRes val title: Int,
    @DrawableRes val logo: Int = R.drawable.ic_edit_religion
) {
    RELIGION_AGNOSTICISM(
        title = R.string.option_religion_agnosticism
    ),
    RELIGION_ATHEISM(
        title = R.string.option_religion_atheism
    ),
    RELIGION_BUDDHISM(
        title = R.string.option_religion_buddhism
    ),
    RELIGION_CATHOLICISM(
        title = R.string.option_religion_catholicism
    ),
    RELIGION_CHRISTIANITY(
        title = R.string.option_religion_christianity
    ),
    RELIGION_HINDUISM(
        title = R.string.option_religion_hinduism
    ),
    RELIGION_JAINISM(
        title = R.string.option_religion_jainism
    ),
    RELIGION_JUDAISM(
        title = R.string.option_religion_judaism
    ),
    RELIGION_MORMONISM(
        title = R.string.option_religion_mormonism
    ),
    RELIGION_ISLAM(
        title = R.string.option_religion_islam
    ),
    RELIGION_ZOROASTRIANISM(
        title = R.string.option_religion_zoroastrianism
    ),
    RELIGION_SIKHISM(
        title = R.string.option_religion_sikhism
    ),
    RELIGION_SPIRITUALISM(
        title = R.string.option_religion_spiritualism
    ),
    RELIGION_OTHER(
        title = R.string.option_religion_other
    ),
    RELIGION_NOT_SET(
        title = R.string.option_not_set
    );

    fun display() = if (this == RELIGION_NOT_SET) R.string.title_not_set else title

    val isValueSet get() = this != RELIGION_NOT_SET

    companion object {
        fun fromStringOrDefault(str: String?): ProfileReligion {
            if (str == null) return RELIGION_CATHOLICISM
            return try {
                ProfileReligion.valueOf(str)
            } catch (ex: Exception) {
                RELIGION_CATHOLICISM
            }
        }
    }
}