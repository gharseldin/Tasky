package com.gharseldin.authentication.presentation.login

sealed interface AuthenticationAction {
    data object onLoginClick : AuthenticationAction
    data object onSignUpClick : AuthenticationAction
    data object OnTogglePasswordVisibilityClick : AuthenticationAction
}
