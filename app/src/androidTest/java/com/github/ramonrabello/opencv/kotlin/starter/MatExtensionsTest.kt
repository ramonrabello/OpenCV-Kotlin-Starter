package com.github.ramonrabello.opencv.kotlin.starter

import android.graphics.drawable.BitmapDrawable
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import android.support.v4.content.ContextCompat
import com.github.ramonrabello.opencv.kotlin.starter.extensions.adaptiveThreshold
import com.github.ramonrabello.opencv.kotlin.starter.extensions.canny
import com.github.ramonrabello.opencv.kotlin.starter.extensions.gaussianBlur
import com.github.ramonrabello.opencv.kotlin.starter.extensions.inGray
import com.github.ramonrabello.opencv.kotlin.starter.extensions.threshold
import com.github.ramonrabello.opencv.kotlin.starter.extensions.toGray
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.opencv.core.Mat

/**
 * Unit tests for Extension functions for Mat
 * located in [MatExtesions] file.
 */
@RunWith(AndroidJUnit4::class)
class MatExtensionsTest {

    private val testBitmap by lazy {
        (ContextCompat.getDrawable(InstrumentationRegistry.getTargetContext(), R.drawable.lena) as BitmapDrawable).bitmap
    }

    @Test
    fun shouldConvertToGray() {
        val mat = Mat()
        mat.toGray(testBitmap)
        assertTrue(mat.inGray())
    }

    @Test
    fun shouldApplyGaussianBlur() {
        val mat = Mat()
        mat.gaussianBlur(testBitmap) { assertNotNull(it) }
    }

    @Test
    fun shouldApplyCannyEdge() {
        val mat = Mat()
        mat.canny(testBitmap) { assertNotNull(it) }
    }

    @Test
    fun shouldCheckIfThresholdWasAppliedToImage() {
        val mat = Mat()
        mat.threshold(testBitmap) { assertNotNull(it) }
    }

    @Test
    fun shouldCheckIfAdaptiveThresholdWasAppliedToImage() {
        val mat = Mat()
        mat.adaptiveThreshold(testBitmap) { assertNotNull(it) }
    }
}