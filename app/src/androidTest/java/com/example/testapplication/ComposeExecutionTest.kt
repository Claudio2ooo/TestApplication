package com.example.testapplication

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.testapplication.compose.ComposeMainActivity
import com.example.testapplication.enums.TestTag
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ComposeExecutionTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComposeMainActivity>()

    @Test
    fun executionLightTest() {
        Thread.sleep(3000)
        repeat(10) {
            composeTestRule.onNodeWithTag(TestTag.HOME.name).performClick()

            composeTestRule.onNodeWithTag(TestTag.SIMPLE_BUTTON_ADD.name).performClick()
            Thread.sleep(500) // per dare tempo al profiler

            composeTestRule.onNodeWithTag(TestTag.STATS.name).performClick()
            Thread.sleep(500)
        }
    }
}