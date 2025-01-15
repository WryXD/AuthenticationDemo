package com.example.authenticationdemo.presentation.components

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun Password(
    modifier: Modifier = Modifier,
    value: () -> String,
    onValueChange: (String) -> Unit,
    onDone: () -> Unit,
    isVisible: () -> Boolean,
    onVisible: () -> Unit,
    leadingIcon: (() -> Int)? = null,
    trailingIcon1: (() -> Int)? = null,
    trailingIcon2: (() -> Int)? = null,
    text: () -> String
) {
    OutlinedTextField(
        value = value(),
        onValueChange = { onValueChange(it) },
        modifier = modifier,
        singleLine = true,
        label = {
            Text(text = text(),
                color = Color.Black)
        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = { onDone() }
        ),
        leadingIcon = {
            if (leadingIcon != null) {
                Icon(
                    painter = painterResource(leadingIcon()),
                    contentDescription = "Leading Icon",
                    tint = Color.Black
                )
            }
        },
        trailingIcon = {
            if (trailingIcon1 != null && trailingIcon2 != null) {
                IconButton(
                    onClick = { onVisible() }
                ) {
                    Icon(
                        painter = if (isVisible()) painterResource(trailingIcon1()) else painterResource(
                            trailingIcon2()
                        ),
                        contentDescription = "Trailing Icon",
                        tint = Color.Black
                    )
                }
            }
        },
        visualTransformation = if (isVisible()) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Black,
            unfocusedIndicatorColor = Color.Black,
            disabledIndicatorColor = Color.Black,
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            disabledContainerColor = Color.White,
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black
        )
    )
}