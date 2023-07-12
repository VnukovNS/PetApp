package com.example.petapp.detailnewsScreen.domain

import com.example.petapp.newsListScreen.data.cloud.NewsDataCloud
import com.example.petapp.detailnewsScreen.presentation.DetailsNewsUi
import com.example.petapp.core.presentation.coil.ImageDownloadResult

class DetailsNewsUiMapper(private val customImageResult: ImageDownloadResult) :
    NewsDataCloud.Mapper<DetailsNewsUi> {

    override fun map(
        author: String,
        content: String,
        date: String,
        imageUrl: String,
        readMoreUrl: String,
        title: String
    ): DetailsNewsUi =
        DetailsNewsUi.Initial(title, customImageResult, content, author, date, readMoreUrl)
}
