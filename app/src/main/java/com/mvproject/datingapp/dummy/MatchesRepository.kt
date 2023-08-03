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
import com.mvproject.datingapp.data.model.UserChatMessage
import com.mvproject.datingapp.data.model.UserHeight
import com.mvproject.datingapp.data.model.UserWork
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes

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
    )
)

val sympathyUsers = listOf(
    SympathyUser(
        id = 1,
        userName = "James",
        userImage = R.drawable.man_1,
        isUserOnline = true
    ),
    SympathyUser(
        id = 2,
        userName = "Adam",
        userImage = R.drawable.man_alter_1,
        isUserOnline = true
    ),
    SympathyUser(
        id = 3,
        userName = "Dasha",
        userImage = R.drawable.woman_1
    ),
    SympathyUser(
        id = 4,
        userName = "John",
        userImage = R.drawable.man_1
    ),
    SympathyUser(
        id = 5,
        userName = "Masha",
        userImage = R.drawable.woman_1
    ),
    SympathyUser(
        id = 6,
        userName = "Peter",
        userImage = R.drawable.man_alter_1
    ),
    SympathyUser(
        id = 7,
        userName = "Jack",
        userImage = R.drawable.man_1
    ),
    SympathyUser(
        id = 8,
        userName = "Natasha",
        userImage = R.drawable.woman_1
    ),
    SympathyUser(
        id = 9,
        userName = "Will",
        userImage = R.drawable.man_alter_1
    )
)

val chatUsers = listOf(
    ChatUser(
        id = 1,
        userName = "James",
        userImage = R.drawable.man_1,
        userLastMessage = "Nice to meet you!",
        userLastMessageTime = System.currentTimeMillis() - 4.hours.inWholeMilliseconds,
        unreadCount = 2
    ),
    ChatUser(
        id = 2,
        userName = "Adam",
        userImage = R.drawable.man_alter_1,
        userLastMessage = "How are you?",
        userLastMessageTime = System.currentTimeMillis() - 25.minutes.inWholeMilliseconds,
        unreadCount = 1
    ),
    ChatUser(
        id = 3,
        userName = "Dasha",
        userImage = R.drawable.woman_1,
        userLastMessage = "Thanks) And you?",
        userLastMessageTime = System.currentTimeMillis() - 2.hours.inWholeMilliseconds,
    ),
    ChatUser(
        id = 4,
        userName = "John",
        userImage = R.drawable.man_1,
        userLastMessage = "You look beautiful",
        userLastMessageTime = System.currentTimeMillis() - 35.minutes.inWholeMilliseconds,
    ),
    ChatUser(
        id = 5,
        userName = "Masha",
        userImage = R.drawable.woman_1,
        userLastMessage = "Where are you from?",
        userLastMessageTime = System.currentTimeMillis() - 1.hours.inWholeMilliseconds,
    ),
    ChatUser(
        id = 6,
        userName = "Peter",
        userImage = R.drawable.man_alter_1,
        userLastMessage = "You have a boyfriend?",
        userLastMessageTime = System.currentTimeMillis() - 3.hours.inWholeMilliseconds,
    ),
    ChatUser(
        id = 7,
        userName = "Jack",
        userImage = R.drawable.man_1,
        userLastMessage = "Nice to meet you!",
        userLastMessageTime = System.currentTimeMillis() - 16.minutes.inWholeMilliseconds,
    ),
    ChatUser(
        id = 8,
        userName = "Natasha",
        userImage = R.drawable.woman_1,
        userLastMessage = "Nice to meet you!",
        userLastMessageTime = System.currentTimeMillis() - 1.hours.inWholeMilliseconds,
    ),
    ChatUser(
        id = 9,
        userName = "Will",
        userImage = R.drawable.man_alter_1,
        userLastMessage = "Nice to meet you!",
        userLastMessageTime = System.currentTimeMillis() - 2.hours.inWholeMilliseconds,
    )
)


fun chatMessages(
    sender: String,
    current: String
) = listOf(
    UserChatMessage(
        message = "hello",
        senderId = sender,
        receiverId = current,
        isMessageLiked = true,
        sendDate = System.currentTimeMillis() - 4.hours.inWholeMilliseconds
    ),
    UserChatMessage(
        message = "how are you",
        senderId = sender,
        receiverId = current,
        sendDate = System.currentTimeMillis() - 3.hours.inWholeMilliseconds
    ),
    UserChatMessage(
        message = "i'm fine",
        receiverId = sender,
        senderId = current,
        sendDate = System.currentTimeMillis() - 3.hours.inWholeMilliseconds
    ),
    UserChatMessage(
        message = "and you",
        receiverId = sender,
        senderId = current,
        isMessageLiked = true,
        sendDate = System.currentTimeMillis() - 2.hours.inWholeMilliseconds
    ),
)