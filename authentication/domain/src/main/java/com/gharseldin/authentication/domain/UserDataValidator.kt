package com.gharseldin.authentication.domain

class UserDataValidator(
    private val patternValidator: PatternValidator
) {

    fun validateName(name: String) = NameValidationState(
        hasMinLength = countCharacters(name) >= MINIMUM_NAME_LENGTH,
        hasMaxLength = countCharacters(name) <= MAXIMUM_NAME_LENGTH,
        isNotEmpty = name.trim().isNotEmpty(),
        isCharactersOnly = name.all { it.isLetter() || it.isWhitespace() }
    )

    private fun countCharacters(text: String): Int {
        var count = 0
        for (c in text) if (c.isLetter()) count++
        return count
    }

    fun isEmailValid(email: String) = patternValidator.matches(email.trim())

    fun validatePassword(password: String) = PasswordValidationState(
        hasMinLength = password.length >= MINIMUM_PASSWORD_LENGTH,
        hasNumber = password.any { it.isDigit() },
        hasLowerCaseCharacter = password.any { it.isLowerCase() },
        hasUpperCaseCharacter = password.any { it.isUpperCase() }
    )

    companion object {
        const val MINIMUM_PASSWORD_LENGTH = 9
        const val MINIMUM_NAME_LENGTH = 4
        const val MAXIMUM_NAME_LENGTH = 50

    }
}
