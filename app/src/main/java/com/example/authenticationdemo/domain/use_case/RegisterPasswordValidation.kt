package com.example.authenticationdemo.domain.use_case

import com.example.authenticationdemo.domain.model.PasswordValidationType
import com.example.authenticationdemo.util.containsNumbers
import com.example.authenticationdemo.util.containsSpecialCharacter
import com.example.authenticationdemo.util.containsUpperCase
import javax.inject.Inject

class RegisterPasswordValidation @Inject constructor() {
    operator fun invoke(password: String): PasswordValidationType {
        if (password.isEmpty()) {
            return PasswordValidationType.EMPTY_FIELD
        }
        if (password.length < MIN_PASSWORD_LENGTH) {
            return PasswordValidationType.PASSWORD_TOO_SHORT
        }

        if (!password.containsSpecialCharacter()) {
            return PasswordValidationType.PASSWORD_MUCH_HAVE_SPECIAL_CHARACTER
        }

        if (!password.containsNumbers()) {
            return PasswordValidationType.PASSWORD_MUCH_HAVE_NUMBER
        }

        if (!password.containsUpperCase()) {
            return PasswordValidationType.PASSWORD_MUCH_HAVE_UPPER_CASE
        }

        return PasswordValidationType.VALID
    }

    companion object {
        const val MIN_PASSWORD_LENGTH = 8
    }
}