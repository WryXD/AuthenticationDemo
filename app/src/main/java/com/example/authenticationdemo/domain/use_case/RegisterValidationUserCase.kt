package com.example.authenticationdemo.domain.use_case

import com.example.authenticationdemo.domain.model.ConfirmPasswordValidationType
import com.example.authenticationdemo.domain.model.EmailValidationType
import com.example.authenticationdemo.domain.model.PasswordValidationType
import javax.inject.Inject

class RegisterValidationUserCase @Inject constructor(
    private val emailValidation: EmailValidation,
    private val passwordValidation: RegisterPasswordValidation,
    private val confirmPasswordValidation: RegisterConfirmPasswordValidation
) {
    fun checkPasswordValidation(password: String): PasswordValidationType {
        return passwordValidation(password)
    }

    fun checkEmailValidation(email: String): EmailValidationType {
        return emailValidation(email)
    }

    fun checkConfirmPasswordValidation(
        password: String,
        confirmPassword: String
    ): ConfirmPasswordValidationType {
        return confirmPasswordValidation(password, confirmPassword)
    }
}