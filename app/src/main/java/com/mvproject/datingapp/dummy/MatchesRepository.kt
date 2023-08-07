/*
 * Create by Medvediev Viktor
 * last update: 24.07.23, 18:17
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.dummy

import com.mvproject.datingapp.R
import com.mvproject.datingapp.data.enums.profile.ProfileInterest
import com.mvproject.datingapp.data.model.UserHeight
import com.mvproject.datingapp.data.model.UserWork

val matchCandidateUsers = listOf(
    MatchUser(
        id = 1,
        name = "James",
        profilePictureUrl = R.drawable.man_1,
        interest = ProfileInterest.INTEREST_DATE,
        photos = listOf(R.drawable.man_1, R.drawable.man_2, R.drawable.man_3),
        birthdate = 775146117000L,
        profileWork = UserWork(
            jobTitle = "Desinger"
        )
    ),
    MatchUser(
        id = 2,
        name = "Adam",
        profileAbout = "I'm looking for a nice girl to meet. I am sociable, I like sports, computer games, traveling. If we have a lot in common, swipe right",
        profilePictureUrl = R.drawable.man_alter_1,
        interest = ProfileInterest.INTEREST_CHAT,
        profileHeight = UserHeight(isHeightNotVisible = true),
        photos = listOf(R.drawable.man_alter_1, R.drawable.man_alter_2, R.drawable.man_alter_3),
        birthdate = 712074117000L
    ),
    MatchUser(
        id = 3,
        name = "Dasha",
        profilePictureUrl = R.drawable.woman_1,
        interest = ProfileInterest.INTEREST_DATE,
        photos = listOf(
            R.drawable.woman_1,
            R.drawable.woman_2,
            R.drawable.woman_3,
            R.drawable.woman_4
        ),
        birthdate = 964534917000L,
        profileWork = UserWork(
            jobTitle = "Desinger",
            jobCompany = "KinectPro"
        )
    ),
    MatchUser(
        id = 4,
        name = "John",
        profilePictureUrl = R.drawable.man_1,
        interest = ProfileInterest.INTEREST_MISC,
        profileHeight = UserHeight(isHeightNotVisible = true),
        photos = listOf(R.drawable.man_1, R.drawable.man_1, R.drawable.man_1),
        birthdate = 964534917000L
    ),
    MatchUser(
        id = 5,
        name = "Masha",
        profileAbout = "I'm looking for a nice boy to meet. I am sociable, I like sports, traveling. If we have a lot in common, swipe right",
        profilePictureUrl = R.drawable.woman_1,
        interest = ProfileInterest.INTEREST_RELATION,
        photos = listOf(
            R.drawable.woman_1,
            R.drawable.woman_2,
            R.drawable.woman_3,
            R.drawable.woman_4
        ),
        birthdate = 901376517000L,
        profileWork = UserWork(
            jobCompany = "KinectPro"
        )
    ),
    MatchUser(
        id = 6,
        name = "Peter",
        profilePictureUrl = R.drawable.man_alter_1,
        profileAbout = "I'm looking for a nice girl to meet. I am sociable, I like sports, computer games, traveling. If we have a lot in common, swipe right",
        interest = ProfileInterest.INTEREST_DATE,
        profileHeight = UserHeight(isHeightNotVisible = true),
        photos = listOf(R.drawable.man_alter_1, R.drawable.man_alter_2, R.drawable.man_alter_3),
        birthdate = 712074117000L
    ),
    MatchUser(
        id = 7,
        name = "Jack",
        profilePictureUrl = R.drawable.man_1,
        interest = ProfileInterest.INTEREST_RELATION,
        photos = listOf(R.drawable.man_1, R.drawable.man_1, R.drawable.man_1),
        birthdate = 869840517000L,
        profileWork = UserWork(
            jobTitle = "Desinger",
            jobCompany = "KinectPro"
        )
    ),
    MatchUser(
        id = 8,
        name = "Natasha",
        profileAbout = "I'm looking for a nice boy to meet. I am sociable, I like sports, traveling. If we have a lot in common, swipe right",
        profilePictureUrl = R.drawable.woman_1,
        interest = ProfileInterest.INTEREST_LOVE,
        photos = listOf(
            R.drawable.woman_1,
            R.drawable.woman_2,
            R.drawable.woman_3,
            R.drawable.woman_4
        ),
        profileHeight = UserHeight(isHeightNotVisible = true),
        birthdate = 838304517000L,
        profileWork = UserWork(
            jobCompany = "KinectPro"
        )
    ),
    MatchUser(
        id = 9,
        name = "Will",
        profilePictureUrl = R.drawable.man_alter_1,
        interest = ProfileInterest.INTEREST_CHAT,
        photos = listOf(R.drawable.man_alter_1, R.drawable.man_alter_2, R.drawable.man_alter_3),
        birthdate = 869840517000L,
        profileWork = UserWork(
            jobTitle = "Desinger"
        )
    ),
)