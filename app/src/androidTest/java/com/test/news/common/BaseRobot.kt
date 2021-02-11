package com.test.news.common

import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.platform.app.InstrumentationRegistry
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.schibsted.spain.barista.interaction.BaristaClickInteractions.clickOn
import org.hamcrest.CoreMatchers


open class BaseTestRobot {

    fun fillEditText(resId: Int, text: String): ViewInteraction =
        onView(ViewMatchers.withId(resId))
            .perform(ViewActions.clearText(), ViewActions.typeText(text))

    fun clickButton(resId: Int) {
        clickOn(resId)
    }

    fun viewIsVisible(resId: Int) {
        assertDisplayed(resId)
    }

    fun viewIsNotVisible(resId: Int):
            ViewInteraction =
        onView(ViewMatchers.withId(resId))
            .check(matches(isDisplayed()))


    fun shortSleep(millis: Long = 350) = apply {
        Thread.sleep(millis)
    }

    fun mediumSleep(millis: Long = 1000) = apply {
        Thread.sleep(millis)
    }

    fun longSleep(millis: Long = 5000) = apply {
        Thread.sleep(millis)
    }

    fun stringIsVisibleIsDescendant(text: String, resId: Int): ViewInteraction =
        onView(
            CoreMatchers.allOf(
                withText(text),
                ViewMatchers.isDescendantOfA(ViewMatchers.withId(resId))
            )
        )

    fun checkToolbarTitleString(expectedText: String): ViewInteraction =
        onView(
            CoreMatchers.allOf(
                ViewMatchers.isAssignableFrom(TextView::class.java),
                ViewMatchers.withParent(ViewMatchers.isAssignableFrom(Toolbar::class.java))
            )
        )
            .check(matches(withText(expectedText)))

    fun imageIsDisplayed(resId: Int) {
        assertDisplayed(resId)
    }

    fun textStringIsVisible(text: String) {
        assertDisplayed(text)
    }

    fun pressItemInList(listRes: Int, position: Int): ViewInteraction =
        onView(ViewMatchers.withId(listRes))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    position,
                    ViewActions.click()
                )
            )

    fun turnOffData() {
        InstrumentationRegistry.getInstrumentation().uiAutomation.executeShellCommand("svc wifi disable")
        InstrumentationRegistry.getInstrumentation().uiAutomation.executeShellCommand("svc data disable")
    }

    fun turnOnData() {
        InstrumentationRegistry.getInstrumentation().uiAutomation.executeShellCommand("svc wifi enable")
        InstrumentationRegistry.getInstrumentation().uiAutomation.executeShellCommand("svc data enable")
    }

}

