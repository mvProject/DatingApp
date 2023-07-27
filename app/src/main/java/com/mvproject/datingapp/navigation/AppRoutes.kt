package com.mvproject.datingapp.navigation

import com.mvproject.datingapp.navigation.NavConstants.ROUTE_CHANGE_PASSWORD
import com.mvproject.datingapp.navigation.NavConstants.ROUTE_DATING_PROFILE
import com.mvproject.datingapp.navigation.NavConstants.ROUTE_EDIT
import com.mvproject.datingapp.navigation.NavConstants.ROUTE_EDIT_OPTION
import com.mvproject.datingapp.navigation.NavConstants.ROUTE_PROFILE_QUESTIONS
import com.mvproject.datingapp.navigation.NavConstants.ROUTE_RESTORE_ACCESS
import com.mvproject.datingapp.navigation.NavConstants.ROUTE_SETTINGS
import com.mvproject.datingapp.navigation.NavConstants.ROUTE_SIGN_IN
import com.mvproject.datingapp.navigation.NavConstants.ROUTE_SIGN_UP

sealed class AppRoutes(val route: String) {
    object SignIn : AppRoutes(ROUTE_SIGN_IN)
    object SignUp : AppRoutes(ROUTE_SIGN_UP)
    object RestoreAccess : AppRoutes(ROUTE_RESTORE_ACCESS)
    object ProfileQuestions : AppRoutes(ROUTE_PROFILE_QUESTIONS)
    object Settings : AppRoutes(ROUTE_SETTINGS)
    object Edit : AppRoutes(ROUTE_EDIT)
    object EditOption : AppRoutes(ROUTE_EDIT_OPTION)
    object DatingProfile : AppRoutes(ROUTE_DATING_PROFILE)
    object ChangePassword : AppRoutes(ROUTE_CHANGE_PASSWORD)
}