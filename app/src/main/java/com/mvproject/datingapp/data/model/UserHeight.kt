/*
 * Create by Medvediev Viktor
 * last update: 04.07.23, 14:42
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.data.model

import com.mvproject.datingapp.utils.STRING_SEPARATOR

data class UserHeight(
    val height: Int = 170,
    val isHeightNotVisible: Boolean = false
) {
    override fun toString(): String {
        return "$height;$isHeightNotVisible"
    }

    companion object {
        fun fromStringOrDefault(str: String?): UserHeight {
            if (str == null) return UserHeight()
            return try {
                val splitted = str.split(STRING_SEPARATOR)
                UserHeight(
                    height = splitted[0].toInt(),
                    isHeightNotVisible = splitted[1].toBoolean(),
                )
            } catch (ex: Exception) {
                UserHeight()
            }
        }
    }
}