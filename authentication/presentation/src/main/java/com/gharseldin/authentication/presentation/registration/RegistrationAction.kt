package com.gharseldin.authentication.presentation.registration

sealed interface RegistrationAction {
    data object OnBackClicked : RegistrationAction
    data object OnGetStartedClicked : RegistrationAction
    data object OnTogglePasswordVisibilityClicked : RegistrationAction
    data class onPasswordFieldFocusChange(val isFocused: Boolean): RegistrationAction
}
