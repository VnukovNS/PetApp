package com.example.petapp.newsListScreen.domain

import com.example.petapp.core.presentation.coil.ImageDownloadResult
import com.example.petapp.newsListScreen.data.cloud.NewsDataCloud
import com.example.petapp.newsListScreen.presentation.NewsPreviewUi

class NewsDataNewsPreviewUiMapper(private val customImageResult: ImageDownloadResult) : NewsDataCloud.Mapper<NewsPreviewUi> {
    override fun map(
        author: String,
        content: String,
        date: String,
        imageUrl: String,
        readMoreUrl: String,
        title: String
    ): NewsPreviewUi = NewsPreviewUi.Base(title, content, customImageResult)
}
