package com.example.authenticationdemo.domain.use_case

import com.example.authenticationdemo.domain.model.ConfirmPasswordValidationType
import com.example.authenticationdemo.domain.model.PasswordValidationType
import junit.framework.TestCase.assertEquals
import org.junit.Test

class RegisterValidationUseCaseTest() {
    private val validation = RegisterValidationUserCase(
        emailValidation = EmailValidation(),
        passwordValidation = RegisterPasswordValidation(),
        confirmPasswordValidation = RegisterConfirmPasswordValidation()
    )

    @Test
    fun `test empty password field return empty field`() {
        val result = validation.checkPasswordValidation(password = "")
        assertEquals(PasswordValidationType.EMPTY_FIELD, result)
    }

    @Test
    fun`test password too short return password too short`(){
        val result = validation.checkPasswordValidation(password = "123")
        assertEquals(PasswordValidationType.PASSWORD_TOO_SHORT, result)

    }

    @Test
    fun `test password much have special character return password much have special character`(){
        val result = validation.checkPasswordValidation(password = "nduyanhcmus")
        assertEquals(PasswordValidationType.PASSWORD_MUCH_HAVE_SPECIAL_CHARACTER, result)
    }

    @Test
    fun `test password much have number return password much have number`(){
        val result = validation.checkPasswordValidation(password = "nduyanhcmus@")
        assertEquals(PasswordValidationType.PASSWORD_MUCH_HAVE_NUMBER, result)
    }

    @Test
    fun `test password much have upper case return password much have upper case`(){
        val result = validation.checkPasswordValidation(password = "nduyanhcmus1@")
        assertEquals(PasswordValidationType.PASSWORD_MUCH_HAVE_UPPER_CASE, result)
    }

    @Test
    fun `test password valid return valid`(){
        val result = validation.checkPasswordValidation(password = "Nduyanhcmus1@")
        assertEquals(PasswordValidationType.VALID, result)

    }
    @Test
    fun `test confirm password not match return confirm password not match`(){
        val result = validation.checkConfirmPasswordValidation(password = "Nduyanhcmus1@", confirmPassword = "Nduyanhcmus2@")
        assertEquals(ConfirmPasswordValidationType.CONFIRM_PASSWORD_NOT_MATCH, result)
    }
    @Test
    fun `test empty confirm password return empty field`(){
        val result = validation.checkConfirmPasswordValidation(password = "Nduyanhcmus1@", confirmPassword = "")
        assertEquals(ConfirmPasswordValidationType.EMPTY_FIELD, result)
    }

    @Test
    fun `test confirm password valid return valid`(){
        val result = validation.checkConfirmPasswordValidation(password = "Nduyanhcmus1@", confirmPassword = "Nduyanhcmus1@")
        assertEquals(ConfirmPasswordValidationType.VALID, result)
    }
}