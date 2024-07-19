package com.gharseldin.authentication.data

import android.util.Patterns
import com.gharseldin.authentication.domain.PatternValidator

object EmailPatternValidator : PatternValidator {
    override fun matches(value: String) = Patterns.EMAIL_ADDRESS.matcher(value).matches()
}
