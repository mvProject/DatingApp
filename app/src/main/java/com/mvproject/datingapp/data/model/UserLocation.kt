/*
 * Create by Medvediev Viktor
 * last update: 04.07.23, 14:42
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.data.model

import com.mvproject.datingapp.dummy.cities
import com.mvproject.datingapp.dummy.countries
import com.mvproject.datingapp.dummy.regions

data class UserLocation(
    val country: String = countries.first(),
    val region: String = regions.first(),
    val city: String = cities.first(),
) {
    override fun toString(): String {
        return "$country;$region;$city"
    }

    companion object {
        fun fromString(s: String): UserLocation {
            val splitted = s.split(";")
            return UserLocation(
                country = splitted[0],
                region = splitted[1],
                city = splitted[2],
            )
        }
    }
}