package com.example.artspace

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.artspace.ui.theme.ArtSpaceTheme

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @get: Rule
    val composeRule = createComposeRule()

    @Test
    fun testImageSlide() {
        composeRule.setContent {
            ArtSpaceTheme {
                ArtSpaceApp()
            }
        }
        for (i in 0..5)
            composeRule.onNodeWithTag("moveLeft").performClick()
    }
}