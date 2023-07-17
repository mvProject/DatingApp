package com.mvproject.datingapp.navigation

import com.mvproject.datingapp.navigation.NavConstants.ROUTE_CHAT
import com.mvproject.datingapp.navigation.NavConstants.ROUTE_DATING
import com.mvproject.datingapp.navigation.NavConstants.ROUTE_LIKES
import com.mvproject.datingapp.navigation.NavConstants.ROUTE_PROFILE
import com.mvproject.datingapp.navigation.NavConstants.ROUTE_PROFILE_QUESTIONS
import com.mvproject.datingapp.navigation.NavConstants.ROUTE_RESTORE_ACCESS
import com.mvproject.datingapp.navigation.NavConstants.ROUTE_SIGN_IN
import com.mvproject.datingapp.navigation.NavConstants.ROUTE_SIGN_UP

sealed class AppRoutes(val route: String) {
    object SignIn : AppRoutes(ROUTE_SIGN_IN)
    object SignUp : AppRoutes(ROUTE_SIGN_UP)
    object RestoreAccess : AppRoutes(ROUTE_RESTORE_ACCESS)
    object ProfileQuestions : AppRoutes(ROUTE_PROFILE_QUESTIONS)

    object Profile : AppRoutes(ROUTE_PROFILE)
    object CHAT : AppRoutes(ROUTE_CHAT)
    object LIKES : AppRoutes(ROUTE_LIKES)
    object Dating : AppRoutes(ROUTE_DATING)


    object BottomBar {
        val routes = listOf(
            Dating, CHAT, LIKES, Profile
        )
    }
}
