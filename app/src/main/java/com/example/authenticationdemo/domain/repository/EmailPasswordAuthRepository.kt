package com.example.authenticationdemo.domain.repository

import com.example.authenticationdemo.domain.model.AuthState

interface EmailPasswordAuthRepository {
    suspend fun login(email: String, password: String): Result<AuthState>
    suspend fun register(
        email: String,
        password: String,
        confirmPassword: String): Result<AuthState>
}