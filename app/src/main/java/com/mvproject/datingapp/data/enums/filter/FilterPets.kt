/*
 * Create by Medvediev Viktor
 * last update: 30.07.23, 16:11
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.data.enums.filter

import androidx.annotation.StringRes
import com.mvproject.datingapp.R
import com.mvproject.datingapp.data.enums.ProfileCharacteristic
import com.mvproject.datingapp.utils.STRING_SEPARATOR

enum class FilterPets(
    @StringRes override val title: Int,
) : ProfileCharacteristic {
    DOG(
        title = R.string.filter_pets_dog
    ),
    PUPPY(
        title = R.string.filter_pets_puppy
    ),
    CAT(
        title = R.string.filter_pets_cat
    ),
    KITTEN(
        title = R.string.filter_pets_kitten
    ),
    PARROT(
        title = R.string.filter_pets_parrot
    ),
    HAMSTER(
        title = R.string.filter_pets_hamster
    ),
    CHINCHILLA(
        title = R.string.filter_pets_chinchilla
    ),
    PIG(
        title = R.string.filter_pets_guinea_pig
    ),
    MOUSE(
        title = R.string.filter_pets_mouse
    ),
    RAT(
        title = R.string.filter_pets_rat
    ),
    SPIDER(
        title = R.string.filter_pets_spider
    ),
    TURTLE(
        title = R.string.filter_pets_turtle
    ),
    SNAKE(
        title = R.string.filter_pets_snake
    ),
    SCORPION(
        title = R.string.filter_pets_scorpion
    ),
    LIZARD(
        title = R.string.filter_pets_lizard
    );

    companion object {
        private val defaultPetList = listOf(
            MOUSE,
            SCORPION,
            LIZARD,
        )

        fun fromStringOrDefault(str: String?): List<FilterPets> {
            if (str == null) return emptyList()
            return try {
                str.split(STRING_SEPARATOR)
                    .toList()
                    .map {
                        FilterPets.valueOf(it)
                    }
            } catch (ex: Exception) {
                emptyList()
            }
        }
    }
}