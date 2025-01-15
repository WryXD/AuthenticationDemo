package com.example.authenticationdemo.presentation

import androidx.compose.foundation.clickable
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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.authenticationdemo.R
import com.example.authenticationdemo.presentation.components.AppButton
import com.example.authenticationdemo.presentation.components.Email
import com.example.authenticationdemo.presentation.components.Password
import com.example.authenticationdemo.presentation.components.SocialSignInButton
import com.example.authenticationdemo.viewmodel.LoginViewModel

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    onSignIn: () -> Unit,
    onSignUp: () -> Unit,
    onForgotPassword: () -> Unit,
    onSignInWithGoogle: () -> Unit,
    onSignInWithFacebook: () -> Unit,
    onSignInWithApple: () -> Unit,
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
    Column(
        Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Chào mừng bạn đã trở lại", color = Color.Black, fontSize = 24.sp
        )

        Spacer(Modifier.height(48.dp))

        Email(modifier = Modifier
            .testTag("email_test")
            .fillMaxWidth(),
            value = { email },
            onValueChange = onUpdateEmail,
            onNext = {
                keyboardAction?.hide()
                focusAction.moveFocus(focusDirection = FocusDirection.Down)
            },
            leadingIcon = { R.drawable.icon_email })

        Spacer(Modifier.height(16.dp))

        Password(modifier = Modifier
            .testTag("password_test")
            .fillMaxWidth(),
            value = { password },
            onValueChange = onUpdatePassword,
            text = { "Password" },
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
            onClick = onSignIn,
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
            Text(
                text = "Chưa có tài khoản?", fontSize = 16.sp, color = Color.Black
            )

            Text(
                text = "Đăng ký ngay",
                color = Color.Blue,
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(start = 4.dp)
                    .clickable { onSignUp() },
                textDecoration = TextDecoration.Underline
            )
        }

    }
}
