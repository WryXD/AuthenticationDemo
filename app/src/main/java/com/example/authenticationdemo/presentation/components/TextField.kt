package com.example.authenticationdemo.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.TextUnit

@Composable
fun TextField(
    modifier : Modifier = Modifier,
    text : () -> String,
    color: () -> Color,
    fontSize: () -> TextUnit,
    textAlignment: () -> TextAlign = { TextAlign.Center },
    textDecoration: () -> TextDecoration = { TextDecoration.None },
    onClick: (() -> Unit)? = null,
    maxLine: () -> Int = { 1 },
    lineHeight: () -> Float = { 1.5f }
){
    Text(
        text = text(),
        color = color(),
        fontSize = fontSize(),
        textAlign = textAlignment(),
        textDecoration = textDecoration(),
        lineHeight = fontSize() * lineHeight(),
        maxLines = maxLine(),
        modifier = modifier.clickable(
            enabled = onClick != null
        ) {
            if(onClick != null){
                onClick()
            }
        }
    )
}