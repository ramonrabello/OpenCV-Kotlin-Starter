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
import com.github.ramonrabello.opencv.kotlin.starter.extensions.toBitmap
import com.github.ramonrabello.opencv.kotlin.starter.extensions.toGray
import com.github.ramonrabello.opencv.kotlin.starter.extensions.toMat
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.opencv.core.Mat

/**
 * Unit tests for Extension functions for Mat
 * located in [BitmapExtensions] file.
 */
@RunWith(AndroidJUnit4::class)
class BitmapExtensionsTest {

    private val testBitmap by lazy {
        (ContextCompat.getDrawable(InstrumentationRegistry.getTargetContext(), R.drawable.lena) as BitmapDrawable).bitmap
    }

    @Test
    fun shouldConvertToMat() {
        assertNotNull(testBitmap.toMat())
    }
}