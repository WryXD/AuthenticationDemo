package com.example.authenticationdemo.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.authenticationdemo.domain.model.ConfirmPasswordValidationType
import com.example.authenticationdemo.domain.model.EmailValidationType
import com.example.authenticationdemo.domain.model.PasswordValidationType
import com.example.authenticationdemo.domain.use_case.RegisterValidationUserCase
import com.example.authenticationdemo.state.RegisterState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val validation: RegisterValidationUserCase,
) : ViewModel() {
    private val _registerState = MutableStateFlow(RegisterState())
    val registerState = _registerState.asStateFlow()

    fun updateEmail(email: String) {
        _registerState.update { it.copy(email = email) }
        checkEmailValidation()
    }

    private fun checkEmailValidation() {
        val validation = validation.checkEmailValidation(_registerState.value.email)
        processEmailValidationType(validation)
    }

    private fun processEmailValidationType(emailValidationType: EmailValidationType) {
        when (emailValidationType) {
            EmailValidationType.EMPTY_FIELD -> _registerState.update {
                it.copy(
                    isEmailError = true,
                    emailErrorMessage = "Email không được để trống!"
                )
            }

            EmailValidationType.INVALID_EMAIL -> _registerState.update {
                it.copy(
                    isEmailError = true,
                    emailErrorMessage = "Email không hợp lệ!"
                )
            }

            EmailValidationType.VALID -> _registerState.update {
                it.copy(
                    isEmailError = false,
                    emailErrorMessage = null
                )
            }
        }
    }

    fun updatePassword(password: String) {
        _registerState.update { it.copy(password = password) }
        checkPasswordValidation()

        if (_registerState.value.confirmPassword.isNotEmpty()) {
            checkConfirmPasswordValidation()
        }

    }

    private fun checkPasswordValidation() {
        val validation = validation.checkPasswordValidation(_registerState.value.password)
        processPasswordValidationType(validation)
    }

    fun updateConfirmPassword(confirmPassword: String) {
        _registerState.update { it.copy(confirmPassword = confirmPassword) }
        checkConfirmPasswordValidation()
    }

    private fun checkConfirmPasswordValidation() {
        val validation = validation.checkConfirmPasswordValidation(
            _registerState.value.password,
            _registerState.value.confirmPassword
        )
        processConfirmPasswordValidationType(validation)
    }

    private fun processConfirmPasswordValidationType(confirmPasswordValidationType: ConfirmPasswordValidationType) {
        when (confirmPasswordValidationType) {
            ConfirmPasswordValidationType.EMPTY_FIELD -> _registerState.update {
                it.copy(
                    isConfirmPasswordError = true,
                    confirmPasswordErrorMessage = "Xác nhận mật khẩu không được để trống!"
                )
            }

            ConfirmPasswordValidationType.CONFIRM_PASSWORD_NOT_MATCH -> _registerState.update {
                it.copy(
                    isConfirmPasswordError = true,
                    confirmPasswordErrorMessage = "Xác nhận mật khẩu không khớp!"
                )
            }

            ConfirmPasswordValidationType.VALID -> _registerState.update {
                it.copy(
                    isConfirmPasswordError = false,
                    confirmPasswordErrorMessage = null
                )
            }
        }
    }

    fun updatePasswordVisibility() {
        _registerState.update { it.copy(isPasswordVisible = !it.isPasswordVisible) }
    }

    fun updateConfirmPasswordVisibility() {
        _registerState.update { it.copy(isConfirmPasswordVisible = !it.isConfirmPasswordVisible) }
    }


    private fun processPasswordValidationType(validation: PasswordValidationType) {
        when (validation) {
            PasswordValidationType.EMPTY_FIELD -> _registerState.update {
                it.copy(
                    isPasswordError = true,
                    passwordErrorMessage = "Mật khẩu không được để trống!"
                )
            }

            PasswordValidationType.PASSWORD_TOO_SHORT -> _registerState.update {
                it.copy(
                    isPasswordError = true,
                    passwordErrorMessage = "Mật khẩu phải có ít nhất 8 ký tự!"
                )
            }

            PasswordValidationType.PASSWORD_MUCH_HAVE_SPECIAL_CHARACTER -> _registerState.update {
                it.copy(
                    isPasswordError = true,
                    passwordErrorMessage = "Mật khẩu phải có ít nhất 1 ký tự đặc biệt!"
                )
            }

            PasswordValidationType.PASSWORD_MUCH_HAVE_NUMBER -> _registerState.update {
                it.copy(
                    isPasswordError = true,
                    passwordErrorMessage = "Mật khẩu phải có ít nhất 1 số!"
                )
            }

            PasswordValidationType.PASSWORD_MUCH_HAVE_UPPER_CASE -> _registerState.update {
                it.copy(
                    isPasswordError = true,
                    passwordErrorMessage = "Mật khẩu phải có ít nhất 1 chữ cái in hoa!"
                )
            }

            PasswordValidationType.VALID -> _registerState.update {
                it.copy(
                    isPasswordError = false,
                    passwordErrorMessage = null
                )
            }
        }
    }
}
