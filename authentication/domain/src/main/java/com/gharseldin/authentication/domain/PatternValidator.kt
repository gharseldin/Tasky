package com.gharseldin.authentication.domain

interface PatternValidator {
    fun matches(value: String): Boolean
}