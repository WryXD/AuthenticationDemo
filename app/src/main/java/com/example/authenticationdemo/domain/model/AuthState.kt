package com.example.authenticationdemo.domain.model

sealed class AuthState {
    data object Success : AuthState()
    data object Loading : AuthState()
    data class Error(val message: String) : AuthState()
}
