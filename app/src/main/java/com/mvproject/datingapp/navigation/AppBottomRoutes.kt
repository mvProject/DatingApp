package com.mvproject.datingapp.navigation

import androidx.annotation.DrawableRes
import com.mvproject.datingapp.ui.screens.main.chat.Chat
import com.mvproject.datingapp.ui.screens.main.dating.navigation.Dating
import com.mvproject.datingapp.ui.screens.main.likes.Likes
import com.mvproject.datingapp.ui.screens.main.profile.view.navigation.Profile

val AppBottomRoutes = listOf(
    Dating, Chat, Likes, Profile
)

data class BottomNavItem(
    val route: String,
    @DrawableRes val logo: Int,
    val isWithBadge: Boolean = false
)