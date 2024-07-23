package com.gharseldin.authentication.presentation.registration

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.text2.input.textAsFlow
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gharseldin.authentication.domain.AuthenticationRepository
import com.gharseldin.authentication.domain.UserDataValidator
import com.gharseldin.authentication.presentation.R
import com.gharseldin.core.domain.util.DataError
import com.gharseldin.core.domain.util.Result
import com.gharseldin.core.presentation.ui.UiText
import com.gharseldin.core.presentation.ui.asUiText
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
class RegistrationScreenViewModel(
    private val authenticationRepository: AuthenticationRepository,
    private val userDataValidator: UserDataValidator
) : ViewModel() {

    var state by mutableStateOf(RegistrationState())

    private val eventChannel = Channel<RegistrationEvent> {}
    val events = eventChannel.receiveAsFlow()

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

    private fun passwordFieldFocusChanged(isFocused: Boolean) {
        state = state.copy(
            isPasswordFieldFocused = isFocused
        )
    }

    fun onAction(action: RegistrationAction) {
        when (action) {
            RegistrationAction.OnBackClicked -> Unit
            RegistrationAction.OnGetStartedClicked -> register()
            RegistrationAction.OnTogglePasswordVisibilityClicked -> {
                state = state.copy(
                    isPasswordVisible = !state.isPasswordVisible
                )
            }

            is RegistrationAction.onPasswordFieldFocusChange -> {
                passwordFieldFocusChanged(action.isFocused)
            }
        }
    }

    private fun register() {
        viewModelScope.launch {
            state = state.copy(isRegistering = true)
            val result = authenticationRepository.register(
                fullName = state.name.text.toString().trim(),
                email = state.email.text.toString().trim(),
                password = state.password.text.toString()
            )
            state = state.copy(
                isRegistering = false
            )

            when (result) {
                is Result.Error -> {

                    if (result.error == DataError.Network.CONFLICT) {
                        eventChannel.send(
                            RegistrationEvent.Error(
                                UiText.StringResource(
                                    R.string.error_email_exists
                                )
                            )
                        )
                    } else {
                        eventChannel.send(
                            RegistrationEvent.Error(
                                result.error.asUiText()
                            )
                        )
                    }
                }

                is Result.Success -> eventChannel.send(RegistrationEvent.RegistrationSuccess)
            }
        }
    }
}
