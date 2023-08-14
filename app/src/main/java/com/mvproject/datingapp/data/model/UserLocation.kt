/*
 * Create by Medvediev Viktor
 * last update: 04.07.23, 14:42
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.data.model

import com.mvproject.datingapp.data.dummy.DEFAULT_LOCATION_INDEX
import com.mvproject.datingapp.data.dummy.cities
import com.mvproject.datingapp.data.dummy.countries
import com.mvproject.datingapp.data.dummy.regions
import com.mvproject.datingapp.utils.STRING_SEPARATOR

data class UserLocation(
    val country: String = countries[DEFAULT_LOCATION_INDEX],
    val region: String = regions[DEFAULT_LOCATION_INDEX],
    val city: String = cities[DEFAULT_LOCATION_INDEX]
) {
    override fun toString(): String {
        return "$country;$region;$city"
    }

    fun display() = "$country, $region, $city"

    companion object {
        fun fromStringOrDefault(s: String?): UserLocation {
            if (s == null) return UserLocation()
            return try {
                val splitted = s.split(STRING_SEPARATOR)
                UserLocation(
                    country = splitted[0],
                    region = splitted[1],
                    city = splitted[2],
                )
            } catch (ex: Exception) {
                UserLocation()
            }
        }
    }
}