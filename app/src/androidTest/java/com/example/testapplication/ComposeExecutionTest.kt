package com.example.testapplication

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.testapplication.compose.ComposeMainActivity
import com.example.testapplication.enums.TestTag
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ComposeExecutionTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComposeMainActivity>()

    @Test
    fun executionLightTest() {

        val scenario = ActivityScenario.launch(ComposeMainActivity::class.java)
        scenario.onActivity { activity ->
            activity.viewModel.setClickIncrement(10)
        }

        Thread.sleep(5000)
        composeTestRule.onNodeWithTag(TestTag.HOME.name).performClick()
        repeat(10) {
            composeTestRule.onNodeWithTag(TestTag.SIMPLE_BUTTON_ADD.name).performClick()
            Thread.sleep(500)
        }

        composeTestRule.onNodeWithTag(TestTag.STATS.name).performClick()
        Thread.sleep(500)

        composeTestRule.onNodeWithTag(TestTag.RESET_SIMPLE_BUTTON.name).performClick()
        Thread.sleep(500)

        composeTestRule.onNodeWithTag(TestTag.RESET_IMAGE_BUTTON.name).performClick()
        Thread.sleep(2500)
    }

    @Test
    fun executionHeavyTest() {

        val scenario = ActivityScenario.launch(ComposeMainActivity::class.java)
        scenario.onActivity { activity ->
            activity.viewModel.setClickIncrement(500)
        }

        Thread.sleep(5000)
        composeTestRule.onNodeWithTag(TestTag.HOME.name).performClick()
        repeat(10) {
            composeTestRule.onNodeWithTag(TestTag.SIMPLE_BUTTON_ADD.name).performClick()
            Thread.sleep(500)

            composeTestRule.onNodeWithTag(TestTag.IMAGE_BUTTON_ADD.name).performClick()
            Thread.sleep(500)

            composeTestRule.onNodeWithTag(TestTag.IMAGE_BUTTON_ADD.name).performClick()
            Thread.sleep(500)

            composeTestRule.onNodeWithTag(TestTag.SIMPLE_BUTTON_ADD.name).performClick()
            Thread.sleep(500)
        }

        composeTestRule.onNodeWithTag(TestTag.STATS.name).performClick()
        Thread.sleep(500)

        composeTestRule.onNodeWithTag(TestTag.RESET_SIMPLE_BUTTON.name).performClick()
        Thread.sleep(500)

        composeTestRule.onNodeWithTag(TestTag.RESET_IMAGE_BUTTON.name).performClick()
        Thread.sleep(2500)
    }
}