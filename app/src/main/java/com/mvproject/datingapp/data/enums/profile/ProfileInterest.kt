/*
 * Create by Medvediev Viktor
 * last update: 24.07.23, 16:18
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.data.enums.profile

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.mvproject.datingapp.R


enum class ProfileInterest(
    @DrawableRes val logo: Int,
    @StringRes val title: Int
) {
    INTEREST_DATE(
        logo = R.drawable.ic_interest_option_date,
        title = R.string.scr_auth_interest_select_date
    ),
    INTEREST_RELATION(
        logo = R.drawable.ic_interest_option_relation,
        title = R.string.scr_auth_interest_select_relationship
    ),
    INTEREST_CHAT(
        logo = R.drawable.ic_interest_option_chat,
        title = R.string.scr_auth_interest_select_chat
    ),
    INTEREST_LOVE(
        logo = R.drawable.ic_interest_option_love,
        title = R.string.scr_auth_interest_select_love
    ),
    INTEREST_MISC(
        logo = R.drawable.ic_interest_option_misc,
        title = R.string.scr_auth_interest_select_misc
    );

    companion object {
        fun fromStringOrDefault(str: String?): ProfileInterest {
            if (str == null) return INTEREST_LOVE
            return try {
                ProfileInterest.valueOf(str)
            } catch (ex: Exception) {
                INTEREST_LOVE
            }
        }
    }
}