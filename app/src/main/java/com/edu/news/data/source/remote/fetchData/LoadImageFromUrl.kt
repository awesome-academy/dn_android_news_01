package com.edu.news.data.source.remote.fetchData

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import java.net.URL
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class LoadImageFromUrl internal constructor(private val imageView: ImageView) {

    private val executor: Executor = Executors.newSingleThreadExecutor()
    private val handler = Handler(Looper.getMainLooper())

    fun loadImage(urlImage: String) {
        executor.execute {
            var imageBitmap: Bitmap? = null
            try {
                val inputStream = URL(urlImage).openStream()
                imageBitmap = Bitmap.createScaledBitmap(
                    BitmapFactory.decodeStream(inputStream),
                    652,
                    336,
                    false
                )
            } catch (e: Exception) {
                e.printStackTrace()
            }
            handler.post {
                try {
                    imageBitmap?.let { imageView.setImageBitmap(it) }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }
}
