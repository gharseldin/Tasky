package com.gharseldin.tasky

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.gharseldin.authentication.presentation.login.LoginScreenRoot
import com.gharseldin.authentication.presentation.registration.RegistrationScreenRoot

@Composable
fun NavigationRoot(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = "auth"
    ) {
        authGraph(navController)
    }
}

private fun NavGraphBuilder.authGraph(navController: NavHostController) {
    navigation(
        startDestination = "login",
        route = "auth"
    ) {
        composable(route = "login") {
            LoginScreenRoot(
                onLoginClick = { },
                onSignUpClick = { navController.navigate("registration") })
        }
        composable(route = "registration") {
            RegistrationScreenRoot(
                onBackClicked = { },
                onSuccessfulRegistration = { navController.navigate("registration") }
            )
        }
    }
}
