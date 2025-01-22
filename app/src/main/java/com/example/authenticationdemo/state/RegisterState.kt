package com.example.authenticationdemo.state

data class RegisterState(
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val isPasswordVisible: Boolean = false,
    val isConfirmPasswordVisible: Boolean = false,
    val isEmailError: Boolean = false,
    val emailErrorMessage: String? = null,
    val isPasswordError: Boolean = false,
    val passwordErrorMessage: String? = null,
    val isConfirmPasswordError: Boolean = false,
    val confirmPasswordErrorMessage: String? = null,
    val isRegisterValid: Boolean = false
)
