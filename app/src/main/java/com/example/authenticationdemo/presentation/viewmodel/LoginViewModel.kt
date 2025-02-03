package com.example.authenticationdemo.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.authenticationdemo.domain.model.AuthState
import com.example.authenticationdemo.domain.model.EmailValidationType
import com.example.authenticationdemo.domain.model.PasswordValidationType
import com.example.authenticationdemo.domain.use_case.LoginValidationUseCase
import com.example.authenticationdemo.domain.use_case.UserLoginUseCase
import com.example.authenticationdemo.state.LoginUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
open class LoginViewModel @Inject constructor(
    private val validation: LoginValidationUseCase,
    private val userLogin: UserLoginUseCase
) : ViewModel() {
    private val _loginUiState = MutableStateFlow(LoginUiState())
    val loginUiState = _loginUiState.asStateFlow()

    private val _loginState = MutableStateFlow<AuthState?>(null)
    val loginState = _loginState.asStateFlow()

    fun updateEmail(email: String) {
        _loginUiState.update { it.copy(email = email) }
        checkEmailValidation()
        Log.e("LoginViewModel", "updateEmail: ${_loginUiState.value.email}")
    }

    private fun checkEmailValidation() {
        val validation = validation.checkEmailValidation(_loginUiState.value.email)
        processEmailValidationType(validation)
    }

    fun updatePassword(password: String) {
        _loginUiState.update { it.copy(password = password) }
        checkPasswordValidation()
    }

    private fun checkPasswordValidation() {
        val validation = validation.checkPasswordValidation(_loginUiState.value.password)
        processPasswordValidationType(validation)
    }

    fun updatePasswordVisibility() {
        _loginUiState.value =
            _loginUiState.value.copy(isPasswordVisible = !_loginUiState.value.isPasswordVisible)
    }

    private fun processEmailValidationType(validationType: EmailValidationType) {
        when (validationType) {
            EmailValidationType.EMPTY_FIELD -> _loginUiState.update {
                it.copy(
                    emailError = true,
                    emailErrorMessage = "Email không được bỏ trống!"
                )
            }

            EmailValidationType.INVALID_EMAIL -> _loginUiState.update {
                it.copy(
                    emailError = true,
                    emailErrorMessage = "Email không hợp lệ!"
                )
            }

            EmailValidationType.VALID -> {
                _loginUiState.update { it.copy(emailError = false, emailErrorMessage = null) }
            }
        }
    }

    private fun processPasswordValidationType(validation: PasswordValidationType) {
        when (validation) {
            PasswordValidationType.EMPTY_FIELD -> _loginUiState.update {
                it.copy(
                    passwordError = true,
                    passwordErrorMessage = "Mật khẩu không được bỏ trống!"
                )
            }

            PasswordValidationType.PASSWORD_TOO_SHORT -> {}
            PasswordValidationType.PASSWORD_MUCH_HAVE_SPECIAL_CHARACTER -> {}
            PasswordValidationType.PASSWORD_MUCH_HAVE_NUMBER -> {}
            PasswordValidationType.PASSWORD_MUCH_HAVE_UPPER_CASE -> {}
            PasswordValidationType.VALID -> _loginUiState.update {
                it.copy(
                    passwordError = false,
                    passwordErrorMessage = null
                )
            }
        }
    }

    fun setProgressBarVisibility(isVisible: Boolean){
        _loginUiState.update { it.copy(isShowProgressBar = isVisible) }
    }

    fun setLoginButtonEnable(isEnable: Boolean){
        _loginUiState.update { it.copy(isEnableButton = isEnable) }
    }

    fun login() {
        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                checkEmailValidation()
                checkPasswordValidation()
                _loginState.value = AuthState.Loading
                delay(800)
            }
            val result = userLogin(_loginUiState.value.email, _loginUiState.value.password)
            when {
                result.isSuccess -> {
                    _loginState.value = AuthState.Success
                    Log.e("LoginViewModel", "login: Success")
                }

                result.isFailure -> {
                    _loginState.value =
                        AuthState.Error(result.exceptionOrNull()?.message ?: "Login failed")
                }
            }
        }
    }
}