package com.example.authenticationdemo

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performImeAction
import androidx.compose.ui.test.performTextInput
import com.example.authenticationdemo.presentation.components.Password
import org.junit.Rule
import org.junit.Test

class PasswordTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun passwordComposableTest() {
        val passwordState = mutableStateOf("")
        val visibilityState = mutableStateOf(false)
        composeTestRule.setContent {
            Box {
                Password(value = { passwordState.value },
                    onValueChange = { passwordState.value = it },
                    onDone = {},
                    text = { "Password" },
                    isError = { false },
                    errorMessage = { "" },
                    isVisible = { visibilityState.value },
                    onVisible = { visibilityState.value = !visibilityState.value },
                    leadingIcon = { R.drawable.icon_lock },
                    trailingIcon1 = { R.drawable.icon_visible },
                    trailingIcon2 = { R.drawable.icon_visible_off })
            }
        }
        // Verify the label is displayed
        composeTestRule.onNodeWithText("Password").assertExists()

        // Verify the initial state of the text field
        composeTestRule.onNodeWithText("").assertExists()

        // Simulate text input
        val inputText = "password123"
        composeTestRule.onNodeWithText("Password").performTextInput(inputText)

        // Verify the leading icon is displayed
        composeTestRule.onNodeWithContentDescription("Leading Icon").assertExists()

        // Verify the trailing icon is displayed
        composeTestRule.onNodeWithContentDescription("Trailing Icon").assertExists()

        // Toggle password visibility
        composeTestRule.onNodeWithContentDescription("Trailing Icon").performClick()

        // Verify the trailing icon is updated based on visibility state
        if (visibilityState.value) {
            composeTestRule.onNodeWithContentDescription("Trailing Icon").assertExists()
        }

    }
}