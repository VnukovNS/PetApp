package com.example.petapp.newsListScreen.data.cloud


interface NewsCloud {

    interface Mapper<T> {
        fun map(category: String, data: MutableList<NewsData.Base>): T
    }

    fun <T> map(mapper: Mapper<T>): T

    data class Base(
        private val category: String = "",
        private val data: MutableList<NewsData.Base>
    ) : NewsCloud {
        override fun <T> map(mapper: Mapper<T>): T = mapper.map(category, data)
    }
}
