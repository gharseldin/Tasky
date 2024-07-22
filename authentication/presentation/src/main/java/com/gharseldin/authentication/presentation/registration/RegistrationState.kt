package com.gharseldin.authentication.presentation.registration

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.text2.input.TextFieldState
import com.gharseldin.authentication.domain.NameValidationState
import com.gharseldin.authentication.domain.PasswordValidationState

data class RegistrationState @OptIn(ExperimentalFoundationApi::class) constructor(
    val name: TextFieldState = TextFieldState(),
    val nameValidationState: NameValidationState = NameValidationState(),
    val email: TextFieldState = TextFieldState(),
    val isEmailValid: Boolean = false,
    val password: TextFieldState = TextFieldState(),
    val passwordValidationState: PasswordValidationState = PasswordValidationState(),
    val isPasswordValid: Boolean = false,
    val isPasswordVisible: Boolean = false,
    val isPasswordFieldFocused: Boolean = false,
    val isRegistering: Boolean = false,
    val canRegister: Boolean = false
)
