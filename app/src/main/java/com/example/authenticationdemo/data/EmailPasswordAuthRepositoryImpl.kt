package com.example.authenticationdemo.data

import android.util.Log
import com.example.authenticationdemo.domain.model.AuthState
import com.example.authenticationdemo.domain.repository.EmailPasswordAuthRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class EmailPasswordAuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : EmailPasswordAuthRepository {
    override suspend fun login(email: String, password: String): Result<AuthState> {
        return try {
            val user = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            if (user.user != null) {
                Result.success(AuthState.Success)
            } else {
                AuthState.Error("User not found!")
                Log.e("EmailPasswordAuthRepositoryImpl", "User not found")
                Result.failure(Exception("Login failed"))
            }
        } catch (e: Exception) {
            Log.e("EmailPasswordAuthRepositoryImpl", "login: ${e.message}")
            AuthState.Error(e.message ?: "Login failed")
            Result.failure(e)
        }
    }

    override suspend fun register(
        email: String,
        password: String,
        confirmPassword: String
    ): Result<AuthState> {
        TODO("Not yet implemented")
    }
}