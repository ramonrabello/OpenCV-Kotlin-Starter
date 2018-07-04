package com.github.ramonrabello.opencv.kotlin.starter

import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.github.ramonrabello.opencv.kotlin.starter.extensions.adaptiveThreshold
import com.github.ramonrabello.opencv.kotlin.starter.extensions.canny
import com.github.ramonrabello.opencv.kotlin.starter.extensions.gaussianBlur
import com.github.ramonrabello.opencv.kotlin.starter.extensions.threshold
import com.github.ramonrabello.opencv.kotlin.starter.extensions.toBitmap
import com.github.ramonrabello.opencv.kotlin.starter.extensions.toGray
import kotlinx.android.synthetic.main.activity_main.toolbar
import kotlinx.android.synthetic.main.content_main.image
import org.opencv.core.Mat

class MainActivity : AppCompatActivity() {

    private val imageBitmap by lazy { (ContextCompat.getDrawable(this, R.drawable.lena) as BitmapDrawable).bitmap }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
    }

    private fun applyGrayScale() {
        val mat = Mat()
        mat.toGray(imageBitmap)
        image.setImageBitmap(mat.toBitmap())
        mat.release()
    }

    private fun applyGaussianBlur(){
        val mat = Mat()
        mat.gaussianBlur(imageBitmap) { image.setImageBitmap(it) }
        mat.release()
    }

    /**
     * Apply the Canny Edge Algorithm to detect edges of an image.
     */
    private fun applyCannyEdge() {
        val mat = Mat()
        mat.canny(imageBitmap) { image.setImageBitmap(it) }
    }

    /**
     * Apply the Threshold Algorithm.
     */
    private fun applyThreshold() {
        val mat = Mat()
        mat.threshold(imageBitmap) { image.setImageBitmap(it) }
    }

    private fun applyAdaptiveThreshold() {
        val mat = Mat()
        mat.adaptiveThreshold(imageBitmap) { image.setImageBitmap(it) }
    }

    private fun resetImage() {
        image.setImageBitmap(imageBitmap)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =// Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
            when (item.itemId) {
                R.id.action_gray_scale -> {
                    applyGrayScale()
                    true
                }
                R.id.action_gaussian_blur -> {
                    applyGaussianBlur()
                    true
                }
                R.id.action_canny -> {
                    applyCannyEdge()
                    true
                }
                R.id.action_threshold -> {
                    applyThreshold()
                    true
                }
                R.id.action_adaptive_threshold -> {
                    applyAdaptiveThreshold()
                    true
                }
                R.id.action_reset -> {
                    resetImage()
                    true
                }
                else -> super.onOptionsItemSelected(item)
            }

}
