package com.mvproject.datingapp.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.mvproject.datingapp.ui.screens.main.chat.navigation.Chat
import com.mvproject.datingapp.ui.screens.main.dating.navigation.Dating
import com.mvproject.datingapp.ui.screens.main.likes.Likes
import com.mvproject.datingapp.ui.screens.main.profile.view.navigation.Profile

val AppBottomRoutes = listOf(
    Dating, Chat, Likes, Profile
)

data class BottomNavItem(
    val route: String,
    @StringRes val title: Int,
    @DrawableRes val logo: Int,
    val isWithBadge: Boolean = false
)