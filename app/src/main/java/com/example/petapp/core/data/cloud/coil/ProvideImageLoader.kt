package com.example.petapp.core.data.cloud.coil

import android.content.Context
import coil.ImageLoader

interface ProvideImageLoader {

    fun provideImageLoader(): ImageLoader

    class Base(private val context: Context) : ProvideImageLoader {
        override fun provideImageLoader(): ImageLoader = ImageLoader.Builder(context)
            .crossfade(true)
            .build()
    }
}
