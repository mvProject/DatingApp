/*
 * Create by Medvediev Viktor
 * last update: 26.06.23, 12:44
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.main.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mvproject.datingapp.data.repository.PreferenceRepository
import com.mvproject.datingapp.navigation.AppBottomRoutes
import com.mvproject.datingapp.navigation.NavConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val preferenceRepository: PreferenceRepository
) : ViewModel() {

    private val _authState = MutableStateFlow(false)
    val authState = _authState.asStateFlow()

    private val _routes = MutableStateFlow(AppBottomRoutes)
    val routes = _routes.asStateFlow()

    init {
        viewModelScope.launch {
            _authState.value = preferenceRepository
                .getUserLoggedState()
        }

        viewModelScope.launch {
            val updatedRoutes = routes.value.map { item ->
                val isBadgeDisplay =
                    item.route == NavConstants.ROUTE_CHAT || item.route == NavConstants.ROUTE_LIKES
                item.copy(isWithBadge = isBadgeDisplay)
            }
            _routes.value = updatedRoutes
        }
    }
}