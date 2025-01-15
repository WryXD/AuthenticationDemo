package com.example.authenticationdemo.presentation.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.authenticationdemo.R

@Composable
fun SocialSignInButton(
    modifier: Modifier = Modifier,
    @DrawableRes image: () -> Int,
    onClick: () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }

    Box(
        modifier = modifier
            .wrapContentSize()
            .size(56.dp)
            .background(Color.White, CircleShape)
            .border(BorderStroke(1.dp, Color.Black), CircleShape)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
            ){
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = image()),
            contentDescription = "Image Button $image",
            modifier = Modifier
                .size(36.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Show() {
    SocialSignInButton(modifier = Modifier.wrapContentWidth(),
        image = { R.drawable.icon_google },
        onClick = {

        })
}