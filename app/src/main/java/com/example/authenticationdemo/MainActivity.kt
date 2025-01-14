package com.example.authenticationdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.authenticationdemo.presentation.LoginScreen
import com.example.authenticationdemo.ui.theme.AuthenticationDemoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AuthenticationDemoTheme {
               LoginScreen(
                   onSignIn = {},
                   onSignUp = {},
                   onForgotPassword = {},
                   onSignInWithGoogle = {},
                   onSignInWithFacebook = {}
               )
            }
        }
    }
}
