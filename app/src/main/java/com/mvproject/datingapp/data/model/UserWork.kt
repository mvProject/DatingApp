/*
 * Create by Medvediev Viktor
 * last update: 04.07.23, 14:42
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.data.model

import com.mvproject.datingapp.utils.STRING_EMPTY
import com.mvproject.datingapp.utils.STRING_SEPARATOR

data class UserWork(
    val jobTitle: String = STRING_EMPTY,
    val jobCompany: String = STRING_EMPTY
) {
    val isBothFilled get() = jobTitle.isNotEmpty() && jobCompany.isNotEmpty()
    val isBothEmpty get() = jobTitle.isEmpty() && jobCompany.isEmpty()
    override fun toString(): String {
        return "$jobTitle;$jobCompany"
    }

    companion object {
        fun fromStringOrDefault(str: String?): UserWork {
            if (str == null) return UserWork()
            return try {
                val splitted = str.split(STRING_SEPARATOR)
                UserWork(
                    jobTitle = splitted[0],
                    jobCompany = splitted[1]
                )
            } catch (ex: Exception) {
                UserWork()
            }
        }
    }
}