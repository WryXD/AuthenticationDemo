package com.example.authenticationdemo.domain.use_case

import com.example.authenticationdemo.domain.model.EmailValidationType
import javax.inject.Inject

class EmailValidation @Inject constructor() {
    operator fun invoke(email: String): EmailValidationType {
        if (email.isEmpty()) {
            return EmailValidationType.EMPTY_FIELD
        }

        if (!email.contains("@") || !email.contains(".")) {
            return EmailValidationType.INVALID_EMAIL
        }
        return EmailValidationType.VALID
    }
}