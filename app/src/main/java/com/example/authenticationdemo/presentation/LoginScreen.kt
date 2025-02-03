package com.example.authenticationdemo.presentation

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.authenticationdemo.R
import com.example.authenticationdemo.domain.model.AuthState
import com.example.authenticationdemo.presentation.components.AppButton
import com.example.authenticationdemo.presentation.components.Email
import com.example.authenticationdemo.presentation.components.Password
import com.example.authenticationdemo.presentation.components.SocialSignInButton
import com.example.authenticationdemo.presentation.components.TextField
import com.example.authenticationdemo.presentation.viewmodel.LoginViewModel

@Composable
fun LoginScreen(
    onSignIn: () -> Unit,
    onSignUp: () -> Unit,
    onForgotPassword: () -> Unit,
    onSignInWithGoogle: () -> Unit,
    onSignInWithFacebook: () -> Unit,
    onSignInWithApple: () -> Unit,
    loginViewModel: LoginViewModel = hiltViewModel(),
) {
    val loginUiState by loginViewModel.loginUiState.collectAsState()
    val loginState by loginViewModel.loginState.collectAsState()

    val keyboard = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    val keyboardAction by remember { mutableStateOf(keyboard) }
    val focusAction by remember { mutableStateOf(focusManager) }


    var email by remember { mutableStateOf(loginUiState.email) }
    var password by remember { mutableStateOf(loginUiState.password) }
    val isPasswordVisible by remember { derivedStateOf { loginUiState.isPasswordVisible } }
    val emailError by remember { derivedStateOf { loginUiState.emailError } }
    val emailErrorMessage by remember { derivedStateOf { loginUiState.emailErrorMessage } }
    val passwordError by remember { derivedStateOf { loginUiState.passwordError } }
    val passwordErrorMessage by remember { derivedStateOf { loginUiState.passwordErrorMessage } }
    val isShowProgressBar by remember { derivedStateOf { loginUiState.isShowProgressBar } }
    val isEnableButton by remember { derivedStateOf { loginUiState.isEnableButton } }

    val onUpdateEmail: (String) -> Unit = remember {
        {
            email = it
            loginViewModel.updateEmail(email)
        }
    }

    val onUpdatePassword: (String) -> Unit = remember {
        {
            password = it
            loginViewModel.updatePassword(password)
        }
    }

    val onVisible: () -> Unit = remember {
        {
            loginViewModel.updatePasswordVisibility()
        }
    }
    val state by remember { derivedStateOf { loginState } }
    HandleLoginState(
        loginState = { state },
        onSignIn = onSignIn,
        loginViewModel = loginViewModel
    )

    Column(
        Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TextField(
            text = { "Chào mừng bạn đã trở lại" },
            color = { Color.Black },
            fontSize = { 24.sp },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(48.dp))

        Email(modifier = Modifier
            .testTag("email_test")
            .fillMaxWidth(),
            value = { email },
            onValueChange = onUpdateEmail,
            onError = { emailError },
            onErrorMessage = { emailErrorMessage ?: "" },
            onNext = {
                keyboardAction?.hide()
                focusAction.moveFocus(focusDirection = FocusDirection.Down)
            },
            leadingIcon = { R.drawable.icon_email }
        )

        Spacer(Modifier.height(8.dp))

        Password(modifier = Modifier
            .testTag("password_test")
            .fillMaxWidth(),
            value = { password },
            onValueChange = onUpdatePassword,
            text = { "Password" },
            isError = { passwordError },
            errorMessage = { passwordErrorMessage ?: "" },
            onDone = {
                keyboardAction?.hide()
            },
            isVisible = { isPasswordVisible },
            onVisible = onVisible,
            leadingIcon = { R.drawable.icon_lock },
            trailingIcon1 = { R.drawable.icon_visible },
            trailingIcon2 = { R.drawable.icon_visible_off })

        Spacer(Modifier.height(24.dp))

        AppButton(
            text = { "Đăng nhập" },
            color = { Color.Blue },
            fontSize = { 16.sp },
            containerColor = { Color.White},
            onClick = loginViewModel::login,
            progressBar = { isShowProgressBar },
            isEnable = { isEnableButton }
        )

        Spacer(Modifier.height(24.dp))

        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {

            HorizontalDivider(
                Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(end = 8.dp)
            )

            Text("Hoặc đăng nhập với", fontSize = 14.sp)

            HorizontalDivider(
                Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(start = 8.dp)
            )
        }

        Spacer(Modifier.height(24.dp))

        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            SocialSignInButton(
                image = { R.drawable.icon_google },
            ) {

            }

            Spacer(Modifier.width(16.dp))

            SocialSignInButton(
                image = { R.drawable.icon_facebook },
            ) {

            }

            Spacer(Modifier.width(16.dp))

            SocialSignInButton(
                image = { R.drawable.icon_apple },
            ) {

            }
        }

        Spacer(Modifier.height(64.dp))

        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            TextField(
                text = { "Chưa có tài khoản?" },
                color = { Color.Black },
                fontSize = { 16.sp },
            )

            TextField(
                text = { "Đăng ký ngay" },
                color = { Color.Blue },
                fontSize = { 16.sp },
                onClick = onSignUp,
                modifier = Modifier.padding(start = 4.dp),
                textDecoration = { TextDecoration.Underline }
            )

        }

    }
}

@Composable
fun HandleLoginState(
    loginState: () -> AuthState?,
    onSignIn: () -> Unit,
    loginViewModel: LoginViewModel,
) {
    LaunchedEffect(loginState()) {
        when (loginState()) {
            is AuthState.Error -> {
                loginViewModel.setProgressBarVisibility(false)
                loginViewModel.setLoginButtonEnable(true)
            }
            is AuthState.Loading -> {
                loginViewModel.setProgressBarVisibility(true)
                loginViewModel.setLoginButtonEnable(false)
            }
            is AuthState.Success -> {
                loginViewModel.setProgressBarVisibility(false)
                loginViewModel.setLoginButtonEnable(true)
                onSignIn()
            }

            null -> {
                Log.e("Login Screen", "Login state is null!")
            }
        }
    }
}