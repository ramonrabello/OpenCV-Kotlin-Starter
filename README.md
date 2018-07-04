# OpenCV-Kotlin-Starter
This is a starter project that integrates OpenCV for Android allowing
to build Computer Vision based apps.

# Motivation
This sample was created to help Android developers to integrate OpenCV in Android Studio. It can be useful when you need to create or adapt your Android apps to
use Computer Vision based algorithms and techniques provided by OpenCV. This project is a mix of the effort result
in research, StackOverflow's answers, and my experience using OpenCV for Android.

# Project Structure
The project is composed basically of 2 modules: `app` and `opencv-lib`. The former is a sample Android application 
module while the later is a library module that has all dependencies and source code of OpenCV to run correctly on
Android Studio and Gradle. If you clone this repository, you will need almost anything to setup regarding 
OpenCV integration. But if you want to do this manually, please read the next section.

# Libraries
This sample app was built using as minimum library as possible in order
to keep it very clean. The current version of this project uses the following dependencies:

- Kotlin JDK 7 Standard Library
- Support Libraries (appcompat-v7, design)
- ConstraintLayout 1.1.2
- JUnit 4 (for Unit Testing)
- Support Test Runner (for running Instrumentation Testing)
- JUnit 4 Test Rules
- Espresso 3.0.2 (for UI Testing)

## Downloading and Setup

1. Go to the [OpenCV releases page](https://opencv.org/releases.html) and click on `Android pack` link of the current version section
2. Unzip the file named `opencv-<version>-android-sdk`
3. In Android Studio, go to __File > New > Import Module...__ and the choose `<opencv_root_folder>/sdk/java` and wait for the sync to finish
4. Create the `jniLibs` folder inside `src/main` and copy the content of `<opencv_root_folder>/sdk/native/libs` to it. This could
take a longer time because all `.so` for all device architecture (x86, mips, ...) for each OpenCV modules (imgproc, imcodecs, highui,...) will also be copied.
__IMPORTANT:__ this process can increase a lot the overall size of the final APK. So, if you want to fine tune and optimize to run in a minimal configuration, just delete the unused modules.
5. Create the folder `aidl` inside `src/main` and copy `org/opencv/engine/OpenCVEngineInterface.aidl` to it.
6. To finish, just import the `opencv-lib` library module inside the `build.gradle` of the `app module`.
7. Sync your project and wait for the build to finish. If all the above steps succeed, _voila!_ Congratulations! You
do had correctly integrated OpenCV inside your app.

# Kotlin Extension Functions for OpenCV
The package `extensions` inside the `app module`
contains some extension functions related to Mat class.
So now, instead of doing the following boilerplate code to
convert a Bitmap using the Canny Edge Transform:
    
    val bitmap = ... // get a Bitmap
    val mat = Mat()
    Utils.bitmapToMat(bitmap, mat)
    Imgproc.cvtColor(mat, mat, Imgproc.COLOR_RGB2GRAY)
    Imgproc.Canny(mat, mat, 50, 50)
    val newBitmap = Bitmap.createBitmap(mat.cols(), mat.rows(), Config.ARGB_8888)
    Utils.matToBitmap(mat, newBitmap)
    imageView.setImageBitmap(newBitmap)
  
Thanks to the great power of Kotlin extension functions,
you can reduce considerably all this boilerplate by doing only this:

    // using default values for thresholds
    mat.canny(srcBitmap) {
        imageView.setImageBitmap(it) // 'it' is the result Bitmap
    }
    
    // using custom values for thresholds
    mat.canny(srcBitmap, 50, 100) {
        imageView.setImageBitmap(it) // it is the result Bitmap
    }
                        
All the pre-processing like converting to gray scale channel, Mat instantiation, Bitmap conversion, etc are being handled
by the `Mat.canny()` extension function. The available extension functions are:
- __Mat.toGray():__ Converts to gray scale channel (from RGB)
- __Mat.gaussianBlur():__ Apply Gaussian Blur Algorithm
- __Mat.canny():__ Apply Canny Edge Algorithm
- __Mat.threshold():__ Apply Threshold Algorithm (BINARY or BINARY_INV)
- __Mat.adaptiveThreshold():__ Apply Adaptive Threshold Algorithm
- __Mat.toBitmap()__: Converts `Mat` to `Bitmap` representation
- __Mat.inGray():__ Checks if Bitmap `Mat` is in gray scale
- __Bitmap.toMat():__ Converts to a `Mat` representation of a Bitmap

More extensions functions will be added in the future. Any pull request, suggestions, bug issues
are welcome to better enhance and evolve this project.

