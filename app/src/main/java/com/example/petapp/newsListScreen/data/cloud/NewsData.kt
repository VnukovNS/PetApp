package com.example.petapp.newsListScreen.data.cloud

interface NewsData {

    interface Mapper<T> {
        fun map(
            author: String,
            content: String,
            date: String,
            imageUrl: String,
            readMoreUrl: String,
            title: String
        ): T
    }

    fun <T> map(mapper: Mapper<T>): T

    data class Base(
        private val author: String = "",
        private val content: String = "",
        private val date: String = "",
        private val imageUrl: String = "",
        //бэк иногда возвращает нуллы
        private var readMoreUrl: String? = "",
        private val title: String = ""
    ) : NewsData {
        override fun <T> map(mapper: Mapper<T>): T {
            if (readMoreUrl == null) readMoreUrl = ""
            return mapper.map(author, content, date, imageUrl, readMoreUrl!!, title)
        }
    }
}
