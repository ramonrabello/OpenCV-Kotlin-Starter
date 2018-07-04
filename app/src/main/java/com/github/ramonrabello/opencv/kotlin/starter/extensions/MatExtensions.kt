package com.github.ramonrabello.opencv.kotlin.starter.extensions

import android.graphics.Bitmap
import org.opencv.android.Utils
import org.opencv.core.CvType
import org.opencv.core.Mat
import org.opencv.core.Size
import org.opencv.imgproc.Imgproc

/**
 * Extension functions for [Mat].
 */
fun Mat.toGray(bitmap: Bitmap) {
    Utils.bitmapToMat(bitmap, this)
    Imgproc.cvtColor(this, this, Imgproc.COLOR_RGB2GRAY)
}

inline fun Mat.gaussianBlur(bitmap: Bitmap, kSize: Size = Size(125.toDouble(), 125.toDouble()), sigmaX:Double = 0.toDouble(), block: (Bitmap) -> Unit) {
    Utils.bitmapToMat(bitmap, this)
    Imgproc.GaussianBlur(this, this, kSize, sigmaX)
    return block(this.toBitmap())
}

inline fun Mat.canny(bitmap: Bitmap, threshold1: Double = 20.toDouble(), threshold2: Double = 255.toDouble(), block: (Bitmap) -> Unit) {
    this.toGray(bitmap)
    Imgproc.Canny(this, this, threshold1, threshold2)
    return block(this.toBitmap())
}

fun Mat.threshold(bitmap: Bitmap, thresh: Double = 50.toDouble(), maxVal: Double = 255.toDouble(), type:Int = Imgproc.THRESH_BINARY, block: (Bitmap) -> Unit) {
    this.toGray(bitmap)
    Imgproc.threshold(this, this, thresh, maxVal, type)
    return block(this.toBitmap())
}

fun Mat.adaptiveThreshold(bitmap: Bitmap, maxValue: Double = 255.toDouble(),
                                  adaptiveMethod: Int = Imgproc.ADAPTIVE_THRESH_MEAN_C,
                                  thresholdType: Int = Imgproc.THRESH_BINARY,
                                  blockSize: Int = 11,
                                  c: Double = 12.toDouble(), block: (Bitmap) -> Unit) {
    this.toGray(bitmap)
    Imgproc.adaptiveThreshold(this, this, maxValue, adaptiveMethod, thresholdType, blockSize, c)
    return block(this.toBitmap())
}

fun Mat.toBitmap(config: Bitmap.Config = Bitmap.Config.ARGB_8888): Bitmap {
    val bitmap = Bitmap.createBitmap(this.cols(), this.rows(), config)
    Utils.matToBitmap(this, bitmap)
    return bitmap
}

fun Mat.inGray() = this.type() == CvType.CV_8U