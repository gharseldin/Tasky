package com.gharseldin.authentication.data.di

import com.gharseldin.authentication.data.EmailPatternValidator
import com.gharseldin.authentication.domain.PatternValidator
import com.gharseldin.authentication.domain.UserDataValidator
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val authDataModule = module {
    single<PatternValidator> {
        EmailPatternValidator
    }
    singleOf(::UserDataValidator)
}
