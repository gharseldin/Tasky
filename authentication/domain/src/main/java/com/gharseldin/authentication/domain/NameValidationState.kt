package com.gharseldin.authentication.domain

data class NameValidationState(
    val hasMinLength: Boolean = false,
    val hasMaxLength: Boolean = false,
    val isNotEmpty: Boolean = false,
    val isCharactersOnly: Boolean = false
) {
    val isNameValid = hasMinLength
            && hasMaxLength
            && isNotEmpty
            && isCharactersOnly
}
