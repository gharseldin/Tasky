package com.gharseldin.authentication.domain

data class PasswordValidationState(
    val hasMinLength: Boolean = false,
    val hasNumber: Boolean = false,
    val hasLowerCaseCharacter: Boolean = false,
    val hasUpperCaseCharacter: Boolean = false
) {
    val isPasswordValid = hasMinLength
            && hasNumber
            && hasLowerCaseCharacter
            && hasUpperCaseCharacter
}