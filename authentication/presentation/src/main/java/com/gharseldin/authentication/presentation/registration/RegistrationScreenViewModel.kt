package com.gharseldin.authentication.presentation.registration

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.text2.input.textAsFlow
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gharseldin.authentication.domain.UserDataValidator
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@OptIn(ExperimentalFoundationApi::class)
class RegistrationScreenViewModel(
    private val userDataValidator: UserDataValidator
) : ViewModel() {

    var state by mutableStateOf(RegistrationState())

    init {
        // Validating every time the name field is edited
        state.name.textAsFlow().onEach { name ->
            val nameValidationState = userDataValidator.validateName(name.toString())
            state = state.copy(
                nameValidationState = nameValidationState,
                canRegister = nameValidationState.isNameValid
                        && state.isEmailValid
                        && state.passwordValidationState.isPasswordValid
            )
        }.launchIn(viewModelScope)

        // Validating every time the email field is edited
        state.email.textAsFlow().onEach { email ->
            val isEmailValid = userDataValidator.isEmailValid(email.toString())
            state = state.copy(
                isEmailValid = isEmailValid,
                canRegister = isEmailValid
                        && state.nameValidationState.isNameValid
                        && state.passwordValidationState.isPasswordValid
            )
        }.launchIn(viewModelScope)

        // Validating every time the password field is edited
        state.password.textAsFlow().onEach { password ->
            val passwordValidationState = userDataValidator.validatePassword(password.toString())
            state = state.copy(
                passwordValidationState = passwordValidationState,
                canRegister = passwordValidationState.isPasswordValid
                        && state.nameValidationState.isNameValid
                        && state.isEmailValid
            )
        }.launchIn(viewModelScope)
    }
}
