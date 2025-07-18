package com.example.testapplication

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.testapplication.compose.ComposeMainActivity
import org.junit.Rule
import org.junit.Test

class ComposeExecutionTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComposeMainActivity>()

    @Test
    fun executionLightTest() {
        repeat(50) {
            composeTestRule.onNodeWithText("Ogni click aggiunge $it elementi").performClick()
            composeTestRule.waitUntil(200) { true }
        }
        composeTestRule.onNodeWithContentDescription("Statistiche").performClick()
        composeTestRule.onNodeWithText("Statistiche").assertIsDisplayed()
    }
}