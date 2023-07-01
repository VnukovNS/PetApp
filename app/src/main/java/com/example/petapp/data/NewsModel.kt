package com.example.petapp.data

//interface NewsModel {
//
//    interface Mapper<T>{
//        fun map(title: String, content: String, imageUrl: String)
//    }

//    fun <T> map(mapper: Mapper<T>): T
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
//    {
//        override fun <T> map(mapper: Mapper<T>): T = mapper.map(title, content, imageUrl)
//    }
//}
