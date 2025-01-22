package com.example.authenticationdemo.util

fun String.containsNumbers(): Boolean {
    return any { it.isDigit() }
}

fun String.containsUpperCase(): Boolean {
    return any { it.isUpperCase() }
}

fun String.containsSpecialCharacter(): Boolean {
    return any { !it.isLetterOrDigit() }
}