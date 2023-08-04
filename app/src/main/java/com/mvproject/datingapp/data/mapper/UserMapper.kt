/*
 * Create by Medvediev Viktor
 * last update: 04.08.23, 16:20
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.data.mapper

import com.mvproject.datingapp.data.dummy.MatchUser
import com.mvproject.datingapp.data.model.PreviewModel
import com.mvproject.datingapp.data.model.User

object UserMapper {
    fun User.toPreview() = with(this) {
        PreviewModel(
            name = name,
            birthdate = birthdate,
            email = email,
            password = password,
            interest = interest,
            gender = gender,
            uid = uid,
            location = location,
            profileAbout = profileAbout,
            profileOrientation = profileOrientation,
            profileMarital = profileMarital,
            profileChildren = profileChildren,
            profileHeight = profileHeight,
            profileZodiac = profileZodiac,
            profileAlcohol = profileAlcohol,
            profileSmoke = profileSmoke,
            profilePsyOrientation = profilePsyOrientation,
            profileReligion = profileReligion,
            profileLanguages = profileLanguages,
            profilePets = profilePets,
            profileWork = profileWork
        )
    }

    fun MatchUser.toPreview() = with(this) {
        PreviewModel(
            id = id,
            name = name,
            birthdate = birthdate,
            email = email,
            password = password,
            interest = interest,
            gender = gender,
            uid = uid,
            location = location,
            profileAbout = profileAbout,
            profileOrientation = profileOrientation,
            profileMarital = profileMarital,
            profileChildren = profileChildren,
            profileHeight = profileHeight,
            profileZodiac = profileZodiac,
            profileAlcohol = profileAlcohol,
            profileSmoke = profileSmoke,
            profilePsyOrientation = profilePsyOrientation,
            profileReligion = profileReligion,
            profileLanguages = profileLanguages,
            profilePets = profilePets,
            profileWork = profileWork
        )
    }
}