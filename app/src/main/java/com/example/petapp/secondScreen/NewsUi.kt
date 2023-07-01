package com.example.petapp.secondScreen

import android.content.Context

interface NewsUi {

    fun map(adapter: NewsAdapter, context: Context)

    data class Initial(private val list: List<NewsPreviewUi>) : NewsUi {
        override fun map(adapter: NewsAdapter, context: Context) = adapter.map(list)
    }
}
