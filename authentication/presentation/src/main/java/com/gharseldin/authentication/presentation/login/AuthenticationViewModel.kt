package com.gharseldin.authentication.presentation.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class AuthenticationViewModel: ViewModel() {

    var state by mutableStateOf(AuthenticationState())
        private set

    fun onAction(action: AuthenticationAction){

    }
}