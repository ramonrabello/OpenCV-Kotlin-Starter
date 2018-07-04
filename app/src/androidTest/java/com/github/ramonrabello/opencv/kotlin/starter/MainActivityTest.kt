package com.github.ramonrabello.opencv.kotlin.starter

import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumentation tests for [MainActivity].
 */
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val activityRule = ActivityTestRule<MainActivity>(MainActivity::class.java)

    private val context = InstrumentationRegistry.getTargetContext()

    @Test
    fun whenActivityStarted_shouldCheckIfAllViewsAreVisible() {
        onView(withId(R.id.textView)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
        onView(withId(R.id.image)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
    }

    @Test
    fun whenOverflowButtonPressed_chooseGrayScaleItem() {
        Espresso.openActionBarOverflowOrOptionsMenu(context)
        onView(withText(context.resources.getString(R.string.action_gray_scale))).perform(click())
    }

    @Test
    fun whenOverflowButtonPressed_chooseGaussianBlurItem() {
        Espresso.openActionBarOverflowOrOptionsMenu(context)
        onView(withText(context.resources.getString(R.string.action_gaussian_blur))).perform(click())
    }

    @Test
    fun whenOverflowButtonPressed_chooseCannyEdgeItem() {
        Espresso.openActionBarOverflowOrOptionsMenu(context)
        onView(withText(context.resources.getString(R.string.action_canny))).perform(click())
    }

    @Test
    fun whenOverflowButtonPressed_chooseThresholdItem() {
        Espresso.openActionBarOverflowOrOptionsMenu(context)
        onView(withText(context.resources.getString(R.string.action_threshold))).perform(click())
    }

    @Test
    fun whenOverflowButtonPressed_chooseAdaptiveThresholdItem() {
        Espresso.openActionBarOverflowOrOptionsMenu(context)
        onView(withText(context.resources.getString(R.string.action_adaptive_threshold))).perform(click())
    }

    @Test
    fun whenOverflowButtonPressed_chooseResetItem() {
        val context = InstrumentationRegistry.getTargetContext()
        Espresso.openActionBarOverflowOrOptionsMenu(context)
        onView(withText(context.resources.getString(R.string.action_reset))).perform(click())
    }
}