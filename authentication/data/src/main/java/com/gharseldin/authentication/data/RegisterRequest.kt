package com.gharseldin.authentication.data

import kotlinx.serialization.Serializable


@Serializable
data class RegisterRequest (
    val fullName: String,
    val email: String,
    val password: String
)
