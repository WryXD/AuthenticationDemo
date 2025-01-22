package com.example.authenticationdemo.domain.use_case

import com.example.authenticationdemo.domain.model.ConfirmPasswordValidationType
import javax.inject.Inject

class RegisterConfirmPasswordValidation @Inject constructor() {
    operator fun invoke(password: String, confirmPassword: String): ConfirmPasswordValidationType {
        if (confirmPassword.isEmpty()) {
            return ConfirmPasswordValidationType.EMPTY_FIELD
        }
        if (password != confirmPassword) {
            return ConfirmPasswordValidationType.CONFIRM_PASSWORD_NOT_MATCH
        }
        return ConfirmPasswordValidationType.VALID
    }
}