package com.example.petapp.core.presentation.coil

import android.graphics.drawable.Drawable
import android.widget.ImageButton
import android.widget.ImageView
import com.example.petapp.R

interface ImageDownloadResult {

    fun showButton(imageButton: ImageButton)
    fun showImage(imageView: ImageView)
    class Success(private val image: Drawable) : ImageDownloadResult {
        override fun showButton(imageButton: ImageButton) =
            imageButton.setImageDrawable(image)

        override fun showImage(imageView: ImageView) =
            imageView.setImageDrawable(image)
    }

    object Error : ImageDownloadResult {
        override fun showButton(imageButton: ImageButton) =
            imageButton.setImageResource(R.drawable.error)

        override fun showImage(imageView: ImageView) =
            imageView.setImageResource(R.drawable.error)
    }
}
