package com.fgarcia.kotlinstars.extension

import androidx.test.platform.app.InstrumentationRegistry
import dagger.hilt.android.testing.HiltTestApplication
import java.io.IOException
import java.io.InputStreamReader

fun String.asJsonString(): String {
    try {
        val inputStream = (InstrumentationRegistry.getInstrumentation().targetContext
            .applicationContext as HiltTestApplication).assets.open(this)
        val builder = StringBuilder()
        InputStreamReader(inputStream, "UTF-8").readLines().forEach {
            builder.append(it)
        }
        return builder.toString()
    } catch (e: IOException) {
        throw e
    }
}