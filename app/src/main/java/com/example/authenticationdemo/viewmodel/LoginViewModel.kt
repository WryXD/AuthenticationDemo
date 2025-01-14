package com.example.authenticationdemo.viewmodel

import androidx.lifecycle.ViewModel
import com.example.authenticationdemo.state.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {
    private val _loginState = MutableStateFlow(LoginState())
    val loginState = _loginState.asStateFlow()

    fun updateEmail(email: String) {
        _loginState.update { it.copy(email = email) }
    }

    fun updatePassword(password: String) {
        _loginState.update { it.copy(password = password) }
    }

    fun updatePasswordVisibility() {
        _loginState.value =
            _loginState.value.copy(isPasswordVisible = !_loginState.value.isPasswordVisible)

    }
}