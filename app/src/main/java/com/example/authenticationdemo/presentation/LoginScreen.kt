package com.example.authenticationdemo.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.authenticationdemo.R
import com.example.authenticationdemo.presentation.components.Email
import com.example.authenticationdemo.presentation.components.Password
import com.example.authenticationdemo.viewmodel.LoginViewModel

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    onSignIn: () -> Unit,
    onSignUp: () -> Unit,
    onForgotPassword: () -> Unit,
    onSignInWithGoogle: () -> Unit,
    onSignInWithFacebook: () -> Unit,
    loginViewModel: LoginViewModel = hiltViewModel(),
) {
    val loginState by loginViewModel.loginState.collectAsState()

    val keyboard = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    val keyboardAction by remember { mutableStateOf(keyboard) }
    val focusAction by remember { mutableStateOf(focusManager) }


    var email by remember { mutableStateOf(loginState.email) }
    var password by remember { mutableStateOf(loginState.password) }
    var isPasswordVisible by remember { mutableStateOf(loginState.isPasswordVisible) }

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
            isPasswordVisible = !isPasswordVisible
            loginViewModel.updatePasswordVisibility()
        }
    }

    Surface(
        modifier
            .fillMaxSize(),
        color = Color.White
    ) {

        Column(
            Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Email(
                modifier = Modifier
                    .testTag("email_test")
                    .fillMaxWidth(),
                value = { email },
                onValueChange = onUpdateEmail,
                onNext = {
                    keyboardAction?.hide()
                    focusAction.moveFocus(focusDirection = FocusDirection.Down)
                },
                leadingIcon = { R.drawable.icon_email }
            )

            Spacer(Modifier.height(16.dp))

            Password(
                modifier = Modifier
                    .testTag("password_test")
                    .fillMaxWidth(),
                value = { password },
                onValueChange = onUpdatePassword,
                onDone = {
                    keyboardAction?.hide()
                },
                isVisible = { isPasswordVisible },
                onVisible = onVisible,
                leadingIcon = { R.drawable.icon_lock },
                trailingIcon1 = { R.drawable.icon_visible },
                trailingIcon2 = { R.drawable.icon_visible_off }
            )
            Spacer(Modifier.height(24.dp))
            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                HorizontalDivider(Modifier.fillMaxWidth(0.33f).padding(end = 8.dp))
                Text("Hoặc đăng nhập với", fontSize = 12.sp)
                HorizontalDivider(Modifier.fillMaxWidth().padding(start = 8.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Show() {

}