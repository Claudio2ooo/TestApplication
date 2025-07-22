package com.example.testapplication

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.testapplication.model.SharedState
import com.example.testapplication.xml.XMLMainActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class XMLExecutionTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(XMLMainActivity::class.java)

    @Test
    fun executionLightTest() {

        SharedState.clickIncrement = 10

        Thread.sleep(5000)
        activityRule.scenario.onActivity {
            it.findViewById<BottomNavigationView>(R.id.bottom_nav).selectedItemId = R.id.nav_home
        }
        repeat(10) {
            onView(withId(R.id.button_add_simple)).perform(click())
            Thread.sleep(500)
        }
        activityRule.scenario.onActivity {
            it.findViewById<BottomNavigationView>(R.id.bottom_nav).selectedItemId = R.id.nav_stats
        }
        Thread.sleep(500)

        onView(withId(R.id.button_reset_simple)).perform(click())
        Thread.sleep(500)

        onView(withId(R.id.button_reset_image)).perform(click())
        Thread.sleep(2500)
    }

    @Test
    fun executionHeavyTest() {

        SharedState.clickIncrement = 500

        Thread.sleep(5000)
        activityRule.scenario.onActivity {
            it.findViewById<BottomNavigationView>(R.id.bottom_nav).selectedItemId = R.id.nav_home
        }
        repeat(10) {
            onView(withId(R.id.button_add_simple)).perform(click())
            Thread.sleep(500)

            onView(withId(R.id.button_add_image)).perform(click())
            Thread.sleep(500)

            onView(withId(R.id.button_add_image)).perform(click())
            Thread.sleep(500)

            onView(withId(R.id.button_add_simple)).perform(click())
            Thread.sleep(500)
        }
        activityRule.scenario.onActivity {
            it.findViewById<BottomNavigationView>(R.id.bottom_nav).selectedItemId = R.id.nav_stats
        }
        Thread.sleep(500)

        onView(withId(R.id.button_reset_simple)).perform(click())
        Thread.sleep(500)

        onView(withId(R.id.button_reset_image)).perform(click())
        Thread.sleep(2500)
    }
}
