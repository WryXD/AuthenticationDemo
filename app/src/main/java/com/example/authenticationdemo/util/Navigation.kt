package com.example.authenticationdemo.util

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.authenticationdemo.presentation.LoginScreen
import com.example.authenticationdemo.presentation.RegisterScreen
import com.example.authenticationdemo.util.Screen.Companion.LOGIN
import com.example.authenticationdemo.util.Screen.Companion.REGISTER

sealed class Screen {
    companion object {
        const val LOGIN = "Login"
        const val REGISTER = "Register"
        const val FORGOT_PASSWORD = "ForgotPassword"
        const val HOME = "Home"
    }
}


@Composable
fun AppNavigation(
    startDestination: String = LOGIN,
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestination) {
        composable(LOGIN) {
            LoginScreen(
                onSignIn = { },
                onSignUp = {
                    navController.navigate(REGISTER) {
                        launchSingleTop = true
                        restoreState = true
                        popUpTo(0)
                    }
                },
                onForgotPassword = {},
                onSignInWithGoogle = { },
                onSignInWithFacebook = {},
                onSignInWithApple = { }
            )
        }
        composable(REGISTER) {
            RegisterScreen(
                onRegister = { },
                onSignUp = {},
                onSignIn = {
                    navController.navigate(LOGIN) {
                        launchSingleTop = true
                        restoreState = true
                        popUpTo(0)
                    }
                },
            )
        }
    }
}
