package com.example.authenticationdemo.domain.use_case

import com.example.authenticationdemo.domain.model.EmailValidationType
import com.example.authenticationdemo.domain.model.PasswordValidationType
import javax.inject.Inject

class LoginValidationUseCase @Inject constructor(
    private val emailValidation: EmailValidation,
    private val passwordValidation: LoginPasswordValidation
){
    fun checkEmailValidation(email: String): EmailValidationType {
        return emailValidation(email)
    }

    fun checkPasswordValidation(password: String): PasswordValidationType {
        return passwordValidation(password)
    }
}