package com.example.authenticationdemo.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
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
import com.example.authenticationdemo.presentation.components.AppButton
import com.example.authenticationdemo.presentation.components.Email
import com.example.authenticationdemo.presentation.components.Password
import com.example.authenticationdemo.presentation.components.TextField
import com.example.authenticationdemo.presentation.viewmodel.RegisterViewModel

@Composable
fun RegisterScreen(
    onRegister: () -> Unit,
    onSignUp: () -> Unit,
    onSignIn: () -> Unit,
    registerViewModel: RegisterViewModel = hiltViewModel(),
) {

    val registerState by registerViewModel.registerState.collectAsState()

    val keyboard = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    val keyboardAction by remember { mutableStateOf(keyboard) }
    val focusAction by remember { mutableStateOf(focusManager) }


    var email by remember { mutableStateOf(registerState.email) }
    var password by remember { mutableStateOf(registerState.password) }
    var confirmPassword by remember { mutableStateOf(registerState.confirmPassword) }
    var isPasswordVisible by remember { mutableStateOf(registerState.isPasswordVisible) }
    val isConfirmPasswordVisible by remember { derivedStateOf { registerState.isConfirmPasswordVisible } }
    val isEmailError by remember { derivedStateOf { registerState.isEmailError } }
    val emailErrorMessage by remember { derivedStateOf { registerState.emailErrorMessage ?: "" } }
    val isPasswordError by remember { derivedStateOf { registerState.isPasswordError } }
    val passwordErrorMessage by remember { derivedStateOf { registerState.passwordErrorMessage ?: "" } }
    val isConfirmPasswordError by remember { derivedStateOf { registerState.isConfirmPasswordError } }
    val confirmPasswordErrorMessage by remember { derivedStateOf { registerState.confirmPasswordErrorMessage ?: "" } }

    val onUpdateEmail: (String) -> Unit = remember {
        {
            email = it
            registerViewModel.updateEmail(email)
        }
    }

    val onUpdatePassword: (String) -> Unit = remember {
        {
            password = it
            registerViewModel.updatePassword(password)
        }
    }

    val onUpdateConfirmPassword: (String) -> Unit = remember {
        {
            confirmPassword = it
            registerViewModel.updateConfirmPassword(confirmPassword)
        }
    }

    val onVisible: () -> Unit = remember {
        {
            isPasswordVisible = !isPasswordVisible
            registerViewModel.updatePasswordVisibility()
        }
    }

    val onVisibleConfirm: () -> Unit = remember {
        {
            registerViewModel.updateConfirmPasswordVisibility()
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
        TextField(
            text = { "Đăng ký tài khoản của bạn ngay bây giờ!" },
            color = { Color.Black },
            fontSize = { 24.sp },
            maxLine = { 2 },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(48.dp))

        Email(modifier = Modifier
            .testTag("email_test")
            .fillMaxWidth(),
            value = { email },
            onValueChange = onUpdateEmail,
            onError = { isEmailError },
            onErrorMessage = { emailErrorMessage },
            onNext = {
                keyboardAction?.hide()
                focusAction.moveFocus(focusDirection = FocusDirection.Down)
            },
            leadingIcon = { R.drawable.icon_email })

        Spacer(Modifier.height(8.dp))

        Password(modifier = Modifier
            .testTag("password_test")
            .fillMaxWidth(),
            value = { password },
            onValueChange = onUpdatePassword,
            text = { "Password" },
            isError = { isPasswordError },
            errorMessage = { passwordErrorMessage },
            onDone = {
                keyboardAction?.hide()
            },
            isVisible = { isPasswordVisible },
            onVisible = onVisible,
            leadingIcon = { R.drawable.icon_lock },
            trailingIcon1 = { R.drawable.icon_visible },
            trailingIcon2 = { R.drawable.icon_visible_off })

        Spacer(Modifier.height(8.dp))

        Password(modifier = Modifier
            .testTag("confirm_password_test")
            .fillMaxWidth(),
            value = { confirmPassword },
            onValueChange = onUpdateConfirmPassword,
            text = { "Confirm Password" },
            isError = { isConfirmPasswordError },
            errorMessage = { confirmPasswordErrorMessage },
            onDone = {
                keyboardAction?.hide()
            },
            isVisible = { isConfirmPasswordVisible },
            onVisible = onVisibleConfirm,
            leadingIcon = { R.drawable.icon_lock },
            trailingIcon1 = { R.drawable.icon_visible },
            trailingIcon2 = { R.drawable.icon_visible_off })

        Spacer(Modifier.height(24.dp))

        AppButton(
            text = { "Đăng ký" },
            color = { Color.Blue },
            fontSize = { 16.sp },
            onClick = {

            },
        )

        Spacer(Modifier.height(64.dp))

        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            TextField(
                text = { "Đã có tài khoản?" },
                color = { Color.Black },
                fontSize = { 16.sp },
            )

            TextField(
                text = { "Đăng nhập ngay" },
                color = { Color.Blue },
                fontSize = { 16.sp },
                onClick = onSignIn,
                modifier = Modifier.padding(start = 4.dp),
                textDecoration = { TextDecoration.Underline }
            )

        }

    }
}