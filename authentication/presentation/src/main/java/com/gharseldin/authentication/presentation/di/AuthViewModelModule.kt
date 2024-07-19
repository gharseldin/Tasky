package com.gharseldin.authentication.presentation.di

import com.gharseldin.authentication.presentation.login.AuthenticationViewModel
import com.gharseldin.authentication.presentation.registration.RegistrationScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val authViewModelModule = module {
    viewModelOf(::RegistrationScreenViewModel)
    viewModelOf(::AuthenticationViewModel)
}