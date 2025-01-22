package com.example.authenticationdemo.state

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val isPasswordVisible: Boolean = false,
    val emailError: Boolean = false,
    val passwordError: Boolean = false,
    val emailErrorMessage: String? = null,
    val passwordErrorMessage: String? = null
)
