package com.example.authenticationdemo

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import com.example.authenticationdemo.presentation.components.Email
import org.junit.Rule
import org.junit.Test

class EmailComposableTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun emailComposableTest() {
        val emailState = mutableStateOf("")

        composeTestRule.setContent {
            Box {
                Email(
                    value = { emailState.value },
                    onValueChange = { emailState.value = it },
                    onNext = { },
                    onError = {false},
                    onErrorMessage = {""},
                    leadingIcon = { R.drawable.icon_email }
                )
            }
        }

        // Verify the label is displayed
        composeTestRule.onNodeWithText("Email").assertExists()

        // Verify the initial state of the text field
        composeTestRule.onNodeWithText("").assertExists()

        // Simulate text input
        val inputText = "test@example.com"
        composeTestRule.onNodeWithText("").performTextInput(inputText)

        composeTestRule.onNodeWithText("Email").assertTextContains(inputText)

        // Verify the state of the text field after input
        composeTestRule.onNodeWithText(inputText).assertExists()

        // Verify the leading icon is displayed
        composeTestRule.onNodeWithContentDescription("Leading Icon").assertExists()
    }
}

