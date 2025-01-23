package com.example.authenticationdemo.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AppButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: () -> String,
    color: () -> Color,
    fontSize: () -> TextUnit,
    containerColor: () -> Color = { Color.White },
    borderColor: () -> Color = { Color.Black },
    borderWidth: () -> Dp = { 1.dp },
    buttonHeight: () -> Dp = { 56.dp },
    buttonWidth: () -> Dp = { 256.dp },
    progressBar: () -> Boolean = { false },
    progressBarSize: () -> Dp = { 24.dp },
    progressBarColor: () -> Color = { Color.Blue },
    progressBarTrackColor: () -> Color = { Color.White }
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .height(buttonHeight())
            .width(buttonWidth()),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor()
        ),
        border = BorderStroke(borderWidth(), borderColor()),
    ) {
        Box(
            Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            if (progressBar()) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(progressBarSize())
                        .align(Alignment.Center),
                    color = progressBarColor(),
                    trackColor = progressBarTrackColor(),
                )
            } else {
                Text(
                    text = text(),
                    color = color(),
                    fontSize = fontSize()
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ShowAppButtonDemo() {
    AppButton(
        onClick = {},
        text = { "Đăng ký" },
        color = { Color.White },
        fontSize = { 16.sp },
        containerColor = { Color.Blue },
        borderColor = { Color.Blue },
        borderWidth = { 2.dp },
        modifier = Modifier.fillMaxWidth(),
        progressBar = { true }
    )
}
