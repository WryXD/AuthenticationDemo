package com.example.authenticationdemo.domain.use_case

import com.example.authenticationdemo.domain.model.EmailValidationType
import com.example.authenticationdemo.domain.model.PasswordValidationType
import junit.framework.TestCase.assertEquals
import org.junit.Test

class LoginValidationUseCaseTest {
    private val validation = LoginValidationUseCase(
        emailValidation = EmailValidation(),
        passwordValidation = LoginPasswordValidation()
    )

    @Test
    fun `test email field return Validation valid `() {
        val result = validation.checkEmailValidation(email = "nduynhanhcmus@gmail.com")
        assertEquals(EmailValidationType.VALID, result)
    }

    @Test
    fun `test empty field return Validation empty field`() {
        val result = validation.checkEmailValidation(email = "")
        assertEquals(EmailValidationType.EMPTY_FIELD, result)
    }

    @Test
    fun `test email field return Validation invalid email`(){
        val result = validation.checkEmailValidation(email = "nduynhanhcmus@gmailcom")
        assertEquals(EmailValidationType.INVALID_EMAIL, result)
    }

    @Test
    fun `test email field return Validation invalid email1`(){
        val result = validation.checkEmailValidation(email = "nduynhanhcmusgmail.com")
        assertEquals(EmailValidationType.INVALID_EMAIL, result)
    }

    @Test
    fun `test password field return Validation valid `() {
        val result = validation.checkPasswordValidation(password = "nduyanhcmus1@")
        assertEquals(PasswordValidationType.VALID, result)

    }

    @Test
    fun `test empty password field return Validation empty field`() {
        val result = validation.checkPasswordValidation(password = "")
        assertEquals(PasswordValidationType.EMPTY_FIELD, result)
    }
}