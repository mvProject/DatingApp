/*
 * Create by Medvediev Viktor
 * last update: 24.07.23, 18:17
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.dummy

import com.mvproject.datingapp.R
import com.mvproject.datingapp.data.enums.ProfileInterest

val matchCandidateUsers = listOf(
    MatchUser(
        name = "James",
        profilePictureUrl = R.drawable.man_1,
        interest = ProfileInterest.INTEREST_DATE.name,
        photos = listOf(R.drawable.man_1, R.drawable.man_1, R.drawable.man_1),
        birthdate = 775146117000L
    ),
    MatchUser(
        name = "Adam",
        profilePictureUrl = R.drawable.man_alter_1,
        interest = ProfileInterest.INTEREST_CHAT.name,
        photos = listOf(R.drawable.man_alter_1, R.drawable.man_alter_2, R.drawable.man_alter_3),
        birthdate = 712074117000L
    ),
    MatchUser(
        name = "Dasha",
        profilePictureUrl = R.drawable.woman_1,
        interest = ProfileInterest.INTEREST_DATE.name,
        photos = listOf(
            R.drawable.woman_1,
            R.drawable.woman_2,
            R.drawable.woman_3,
            R.drawable.woman_4
        ),
        birthdate = 964534917000L
    ),
    MatchUser(
        name = "John",
        profilePictureUrl = R.drawable.man_1,
        interest = ProfileInterest.INTEREST_MISC.name,
        photos = listOf(R.drawable.man_1, R.drawable.man_1, R.drawable.man_1),
        birthdate = 964534917000L
    ),
    MatchUser(
        name = "Masha",
        profilePictureUrl = R.drawable.woman_1,
        interest = ProfileInterest.INTEREST_RELATION.name,
        photos = listOf(
            R.drawable.woman_1,
            R.drawable.woman_2,
            R.drawable.woman_3,
            R.drawable.woman_4
        ),
        birthdate = 901376517000L
    ),
    MatchUser(
        name = "Peter",
        profilePictureUrl = R.drawable.man_alter_1,
        interest = ProfileInterest.INTEREST_DATE.name,
        photos = listOf(R.drawable.man_alter_1, R.drawable.man_alter_2, R.drawable.man_alter_3),
        birthdate = 712074117000L
    ),
    MatchUser(
        name = "Jack",
        profilePictureUrl = R.drawable.man_1,
        interest = ProfileInterest.INTEREST_RELATION.name,
        photos = listOf(R.drawable.man_1, R.drawable.man_1, R.drawable.man_1),
        birthdate = 869840517000L
    ),
    MatchUser(
        name = "Natasha",
        profilePictureUrl = R.drawable.woman_1,
        interest = ProfileInterest.INTEREST_LOVE.name,
        photos = listOf(
            R.drawable.woman_1,
            R.drawable.woman_2,
            R.drawable.woman_3,
            R.drawable.woman_4
        ),
        birthdate = 838304517000L
    ),
    MatchUser(
        name = "Will",
        profilePictureUrl = R.drawable.man_alter_1,
        interest = ProfileInterest.INTEREST_CHAT.name,
        photos = listOf(R.drawable.man_alter_1, R.drawable.man_alter_2, R.drawable.man_alter_3),
        birthdate = 869840517000L
    ),
)