package com.github.ramonrabello.opencv.kotlin.starter

import android.app.Application
import android.util.Log
import org.opencv.android.InstallCallbackInterface
import org.opencv.android.LoaderCallbackInterface
import org.opencv.android.OpenCVLoader
import org.opencv.android.OpenCVLoader.initAsync

class StarterApplication : Application() {

    companion object {
        const val TAG = "StarterApplication"
    }

    override fun onCreate() {
        super.onCreate()
        initOpenCV()
    }

    private fun initOpenCV() {
        val wasEngineInitialized = OpenCVLoader.initDebug()
        if (wasEngineInitialized){
            Log.d(TAG, "The OpenCV was successfully initialized in debug mode using .so libs.")
        } else {
            initAsync(OpenCVLoader.OPENCV_VERSION_3_4_0, this, object : LoaderCallbackInterface {
                override fun onManagerConnected(status: Int) {
                    when(status) {
                        LoaderCallbackInterface.SUCCESS -> Log.d(TAG,"OpenCV successfully started.")
                        LoaderCallbackInterface.INIT_FAILED -> Log.d(TAG,"Failed to start OpenCV.")
                        LoaderCallbackInterface.MARKET_ERROR -> Log.d(TAG,"Google Play Store could not be invoked. Please check if you have the Google Play Store app installed and try again.")
                        LoaderCallbackInterface.INSTALL_CANCELED -> Log.d(TAG,"OpenCV installation has been cancelled by the user.")
                        LoaderCallbackInterface.INCOMPATIBLE_MANAGER_VERSION -> Log.d(TAG,"This version of OpenCV Manager is incompatible. Possibly, a service update is required.")
                    }
                }

                override fun onPackageInstall(operation: Int, callback: InstallCallbackInterface?) {
                    Log.d(TAG,"OpenCV Manager successfully installed from Google Play.")
                }
            })
        }
    }
}