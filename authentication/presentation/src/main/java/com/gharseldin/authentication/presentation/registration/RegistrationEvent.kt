package com.gharseldin.authentication.presentation.registration

import com.gharseldin.core.presentation.ui.UiText

sealed interface RegistrationEvent {
    data object RegistrationSuccess : RegistrationEvent
    data class Error(val error: UiText) : RegistrationEvent
}
