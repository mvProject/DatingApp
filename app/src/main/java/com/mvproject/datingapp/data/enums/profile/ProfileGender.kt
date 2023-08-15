/*
 * Create by Medvediev Viktor
 * last update: 09.06.23, 17:59
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.data.enums.profile


enum class ProfileGender {
    MALE,
    FEMALE;

    companion object {
        fun fromStringOrDefault(str: String?): ProfileGender {
            if (str == null) return MALE
            return try {
                ProfileGender.valueOf(str)
            } catch (ex: Exception) {
                MALE
            }
        }
    }
}