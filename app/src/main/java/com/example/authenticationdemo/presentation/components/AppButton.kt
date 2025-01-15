package com.example.authenticationdemo.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp

@Composable
fun AppButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: () -> String,
    color: () -> Color,
    fontSize: () -> TextUnit,
    containerColor: () -> Color = { Color.White },
    borderColor: () -> Color = { Color.Black },
    borderWidth: () -> Dp = { 1.dp},
    buttonHeight: () -> Dp = { 56.dp }
) {
    Button(
        onClick = onClick,
        modifier = modifier.height(buttonHeight()),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor()
        ),
        border = BorderStroke(borderWidth(), borderColor())
    ) {
        Text(
            text = text(),
            color = color(),
            fontSize = fontSize()
        )
    }
}