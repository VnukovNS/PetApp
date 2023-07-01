package com.example.petapp.data


// TODO: сделать маппинг
data class NewsModel(
        val category: String = "",
        val data: MutableList<NewsData>
    )

    data class NewsData(
        val author: String = "",
        val content: String = "",
        val date: String = "",
        val imageUrl: String = "",
        val readMoreUrl: String = "",
        val title: String = ""
    )
