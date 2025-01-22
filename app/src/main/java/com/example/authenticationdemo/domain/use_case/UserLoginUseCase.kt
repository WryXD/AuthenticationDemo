package com.example.authenticationdemo.domain.use_case

import com.example.authenticationdemo.data.EmailPasswordAuthRepositoryImpl
import com.example.authenticationdemo.domain.model.AuthState
import javax.inject.Inject

class UserLoginUseCase@Inject constructor(
    private val authRepository: EmailPasswordAuthRepositoryImpl
) {
    suspend operator fun invoke(email:String, password:String): Result<AuthState> {
        return authRepository.login(email, password)
    }
}