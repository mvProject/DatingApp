package com.mvproject.datingapp.navigation

import com.mvproject.datingapp.navigation.NavConstants.ROUTE_ACTIVATION
import com.mvproject.datingapp.navigation.NavConstants.ROUTE_CANDIDATE_FULL_PREVIEW
import com.mvproject.datingapp.navigation.NavConstants.ROUTE_CHANGE_PASSWORD
import com.mvproject.datingapp.navigation.NavConstants.ROUTE_CHAT_MESSAGE
import com.mvproject.datingapp.navigation.NavConstants.ROUTE_DATING_FILTER
import com.mvproject.datingapp.navigation.NavConstants.ROUTE_EDIT
import com.mvproject.datingapp.navigation.NavConstants.ROUTE_EDIT_OPTION
import com.mvproject.datingapp.navigation.NavConstants.ROUTE_MATCH_PROFILE
import com.mvproject.datingapp.navigation.NavConstants.ROUTE_PROFILE_FULL_PREVIEW
import com.mvproject.datingapp.navigation.NavConstants.ROUTE_PROFILE_PREVIEW
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
    object CandidatePreview : AppRoutes(ROUTE_CANDIDATE_FULL_PREVIEW)
    object ProfileFullPreview : AppRoutes(ROUTE_PROFILE_FULL_PREVIEW)
    object ProfilePreview : AppRoutes(ROUTE_PROFILE_PREVIEW)
    object MatchProfile : AppRoutes(ROUTE_MATCH_PROFILE)
    object DatingFilter : AppRoutes(ROUTE_DATING_FILTER)
    object ChangePassword : AppRoutes(ROUTE_CHANGE_PASSWORD)
    object ChatMessage : AppRoutes(ROUTE_CHAT_MESSAGE)
    object Activation : AppRoutes(ROUTE_ACTIVATION)
}