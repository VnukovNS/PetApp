package com.example.petapp.detailnewsScreen.data

import com.example.petapp.newsListScreen.data.cloud.NewsData
import com.example.petapp.detailnewsScreen.presentation.DetailsNewsUi

class DetailsNewsUiMapper : NewsData.Mapper<DetailsNewsUi> {
    override fun map(
        author: String,
        content: String,
        date: String,
        imageUrl: String,
        readMoreUrl: String,
        title: String
    ): DetailsNewsUi = DetailsNewsUi.Initial(title, content, author, date, readMoreUrl)

}