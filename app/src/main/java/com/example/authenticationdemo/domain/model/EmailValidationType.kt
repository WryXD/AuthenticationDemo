package com.example.authenticationdemo.domain.model

enum class EmailValidationType {
    EMPTY_FIELD,          // Email or Password field is empty
    INVALID_EMAIL,        // Email is not in a valid format
    VALID,
}