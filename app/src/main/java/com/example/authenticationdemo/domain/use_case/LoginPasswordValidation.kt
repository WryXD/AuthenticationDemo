package com.example.authenticationdemo.domain.use_case

import com.example.authenticationdemo.domain.model.PasswordValidationType
import javax.inject.Inject

class LoginPasswordValidation @Inject constructor() {
    operator fun invoke(password: String): PasswordValidationType {
        if (password.isEmpty()) {
            return PasswordValidationType.EMPTY_FIELD
        }
        return PasswordValidationType.VALID
    }
}