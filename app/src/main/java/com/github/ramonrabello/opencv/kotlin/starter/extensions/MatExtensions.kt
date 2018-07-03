package com.github.ramonrabello.opencv.kotlin.starter.extensions

import android.graphics.Bitmap
import org.opencv.android.Utils
import org.opencv.core.Mat
import org.opencv.imgproc.Imgproc

/**
 * Extension functions for [Mat].
 */
fun <T : Mat> T.toGray(bitmap: Bitmap) {
    Utils.bitmapToMat(bitmap, this)
    Imgproc.cvtColor(this, this, Imgproc.COLOR_RGB2GRAY)
}

fun <T : Mat> T.threshold(bitmap: Bitmap, thresh: Double = 50.toDouble(), maxVal: Double = 255.toDouble()): T {
    this.toGray(bitmap)
    Imgproc.threshold(this, this, thresh, maxVal, Imgproc.THRESH_BINARY)
    return this
}

fun <T : Mat> T.adaptiveThreshold(bitmap: Bitmap, maxValue: Double = 255.toDouble(),
                                  adaptiveMethod: Int = Imgproc.ADAPTIVE_THRESH_MEAN_C,
                                  thresholdType: Int = Imgproc.THRESH_BINARY,
                                  blockSize: Int = 11,
                                  c: Double = 12.toDouble()): T {
    this.toGray(bitmap)
    Imgproc.adaptiveThreshold(this, this, maxValue, adaptiveMethod, thresholdType, blockSize, c)
    return this
}

fun <T : Mat> T.canny(bitmap: Bitmap, threshold1: Double = 20.toDouble(), threshold2: Double = 255.toDouble()): T {
    this.toGray(bitmap)
    Imgproc.Canny(this, this, threshold1, threshold2)
    return this
}

fun <T : Mat> T.toBitmap(config: Bitmap.Config = Bitmap.Config.ARGB_8888): Bitmap {
    val bitmap = Bitmap.createBitmap(this.cols(), this.rows(), config)
    Utils.matToBitmap(this, bitmap)
    return bitmap
}