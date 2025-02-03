package com.example.authenticationdemo.presentation.components

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
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

@Composable
fun Email(
    modifier: Modifier = Modifier,
    value: () -> String,
    onValueChange: (String) -> Unit,
    onNext: () -> Unit,
    onError: () -> Boolean,
    onErrorMessage: () -> String,
    leadingIcon: (() -> Int?)? = null,
) {
    OutlinedTextField(
        value = value(),
        onValueChange = { onValueChange(it) },
        isError = onError(),
        modifier = modifier,
        singleLine = true,
        supportingText = {
            Text(
                text = onErrorMessage(),
                color = Color.Black
            )
        },
        label = {
            val text by remember { mutableStateOf("Email") }
            Text(
                text = text,
                color = Color.Black
            )
        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next
        ),
        keyboardActions = KeyboardActions(
            onNext = { onNext() }
        ),
        leadingIcon = {
            if (leadingIcon != null) {
                Icon(
                    painterResource(leadingIcon()!!),
                    contentDescription = "Leading Icon",
                    tint = Color.Black
                )
            }
        },
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Black,
            unfocusedIndicatorColor = Color.Black,
            disabledIndicatorColor = Color.Black,
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            disabledContainerColor = Color.White,
            errorIndicatorColor = Color.Red,
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black,
            errorContainerColor = Color.White
        )
    )

}