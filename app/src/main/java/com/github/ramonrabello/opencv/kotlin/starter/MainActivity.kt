package com.github.ramonrabello.opencv.kotlin.starter

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.image
import org.opencv.android.Utils
import org.opencv.core.Mat
import org.opencv.imgproc.Imgproc

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
    }

    override fun onResume() {
        super.onResume()
        convertToGrayScale()
    }

    /**
     * Convert a bitmap to gray scale using OpenCV.
     */
    private fun convertToGrayScale() {

        // instantiates a Mat (a matrix representation of an image in OpenCV). Best practices: use
        // less Mat objects as possible once each new Mat's instance is a new malloc() call in C/C++.
        val grayMat = Mat()

        // gets the ImageView's drawable as bitmap to be used
        // to Mat conversion
        val imageBitmap = (image.drawable as BitmapDrawable).bitmap

        // converts the bitmap to Mat representation
        Utils.bitmapToMat(imageBitmap, grayMat)

        // converts the Mat to gray scale representation
        Imgproc.cvtColor(grayMat, grayMat, Imgproc.COLOR_BGRA2GRAY)

        // create a bitmap based on the gray-scaled Mat
        val grayBitmap = Bitmap.createBitmap(grayMat.cols(), grayMat.rows(), Bitmap.Config.ARGB_8888)

        // converts back from Mat to Bitmap in order to
        // show the Bitmap on ImageView
        Utils.matToBitmap(grayMat, grayBitmap)

        // displays back the converted Bitmap on ImageView
        image.setImageBitmap(grayBitmap)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
