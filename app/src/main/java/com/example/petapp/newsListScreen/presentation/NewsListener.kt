package com.example.petapp.newsListScreen.presentation


interface NewsListener {

    fun openFullNews(item: NewsPreviewUi, position: Int)
}
