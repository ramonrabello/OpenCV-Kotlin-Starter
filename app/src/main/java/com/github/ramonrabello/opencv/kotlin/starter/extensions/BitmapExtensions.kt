package com.github.ramonrabello.opencv.kotlin.starter.extensions

import android.graphics.Bitmap
import org.opencv.android.Utils
import org.opencv.core.Mat

/**
 * Extension functions for [Bitmap].
 */
fun Bitmap.toMat() : Mat {
    val mat = Mat()
    Utils.bitmapToMat(this, mat)
    return mat
}