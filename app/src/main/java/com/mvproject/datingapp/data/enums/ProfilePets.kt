/*
 * Create by Medvediev Viktor
 * last update: 15.06.23, 13:04
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.data.enums

import androidx.annotation.StringRes
import com.mvproject.datingapp.R
import com.mvproject.datingapp.utils.STRING_SEPARATOR

enum class ProfilePets(
    @StringRes val title: Int
) {
    PETS_DOG(
        title = R.string.option_pets_dog
    ),
    PETS_PUPPY(
        title = R.string.option_pets_puppy
    ),
    PETS_CAT(
        title = R.string.option_pets_cat
    ),
    PETS_KITTEN(
        title = R.string.option_pets_kitten
    ),
    PETS_PARROT(
        title = R.string.option_pets_parrot
    ),
    PETS_HAMSTER(
        title = R.string.option_pets_hamster
    ),
    PETS_CHINCHILLA(
        title = R.string.option_pets_chinchilla
    ),
    PETS_PIG(
        title = R.string.option_pets_guinea_pig
    ),
    PETS_MOUSE(
        title = R.string.option_pets_mouse
    ),
    PETS_RAT(
        title = R.string.option_pets_rat
    ),
    PETS_SPIDER(
        title = R.string.option_pets_spider
    ),
    PETS_TURTLE(
        title = R.string.option_pets_turtle
    ),
    PETS_SNAKE(
        title = R.string.option_pets_snake
    ),
    PETS_SCORPION(
        title = R.string.option_pets_scorpion
    ),
    PETS_LIZARD(
        title = R.string.option_pets_lizard
    ),
    PETS_NO(
        title = R.string.option_pets_no
    );

    companion object {
        val defaultPetList = listOf(
            PETS_MOUSE,
            PETS_SCORPION,
            PETS_LIZARD,
        )

        fun fromStringOrDefault(str: String?): List<ProfilePets> {
            if (str == null) return defaultPetList
            return try {
                str.split(STRING_SEPARATOR)
                    .toList()
                    .map {
                        ProfilePets.valueOf(it)
                    }
            } catch (ex: Exception) {
                emptyList()
            }
        }
    }
}