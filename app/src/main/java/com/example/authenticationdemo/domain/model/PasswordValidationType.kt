package com.example.authenticationdemo.domain.model

enum class PasswordValidationType {
    EMPTY_FIELD,
    PASSWORD_TOO_SHORT,
    PASSWORD_MUCH_HAVE_SPECIAL_CHARACTER,
    PASSWORD_MUCH_HAVE_NUMBER,
    PASSWORD_MUCH_HAVE_UPPER_CASE,
    VALID
}