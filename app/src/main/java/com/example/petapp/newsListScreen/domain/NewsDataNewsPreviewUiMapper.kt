package com.example.petapp.newsListScreen.domain

import com.example.petapp.newsListScreen.data.cloud.NewsData
import com.example.petapp.newsListScreen.presentation.NewsPreviewUi

class NewsDataNewsPreviewUiMapper : NewsData.Mapper<NewsPreviewUi> {
    override fun map(
        author: String,
        content: String,
        date: String,
        imageUrl: String,
        readMoreUrl: String,
        title: String
    ): NewsPreviewUi = NewsPreviewUi.Base(title, content)
}