package com.example.petapp.core.data.cloud.coil

import android.content.Context
import coil.ImageLoader
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.example.petapp.core.presentation.coil.ImageDownloadResult

interface ImageDownload {

    suspend fun getImage(url: String): ImageDownloadResult

    class Base(
        private val context: Context,
        private val imageLoader: ImageLoader
    ) : ImageDownload {
        override suspend fun getImage(url: String): ImageDownloadResult {
            // todo подумать чтобы уменшьить кэш? а ьакже обрезку картинок и тд.
            val request = ImageRequest.Builder(context)
                .data(url)
                .memoryCachePolicy(CachePolicy.DISABLED)
                .build()
            val result = imageLoader.execute(request)
            return if (result.drawable != null) {
                ImageDownloadResult.Success(result.drawable!!)
            } else {
                ImageDownloadResult.Error
            }
        }
    }
}
