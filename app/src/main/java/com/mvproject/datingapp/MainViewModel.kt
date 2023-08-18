/*
 * Create by Medvediev Viktor
 * last update: 07.06.23, 14:17
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mvproject.datingapp.utils.DELAY_1000
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    private val _isLoading: MutableState<Boolean> = mutableStateOf(true)
    val isLoading: State<Boolean> = _isLoading

    init {
        viewModelScope.launch {
            delay(DELAY_1000)
            _isLoading.value = false
        }
    }
}