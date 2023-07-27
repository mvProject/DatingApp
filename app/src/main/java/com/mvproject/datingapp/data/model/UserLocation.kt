/*
 * Create by Medvediev Viktor
 * last update: 04.07.23, 14:42
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.data.model

import com.mvproject.datingapp.dummy.DEFAULT_LOCATION_INDEX
import com.mvproject.datingapp.dummy.cities
import com.mvproject.datingapp.dummy.countries
import com.mvproject.datingapp.dummy.regions
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
        fun fromString(s: String): UserLocation {
            val splitted = s.split(STRING_SEPARATOR)
            return try {
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