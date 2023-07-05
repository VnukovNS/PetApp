package com.example.petapp.newsListScreen.data.cache

import com.example.petapp.newsListScreen.data.cloud.NewsData

interface NewsList {

    interface Mutable {

        fun save(data: List<NewsData.Base>)

        fun saveId(id: Int)

        fun read(): NewsData.Base
    }

    class Base : Mutable {
        private var value: List<NewsData.Base> = listOf()
        private var id: Int = -1

        override fun save(data: List<NewsData.Base>) {
            value = data
        }

        override fun saveId(id: Int) {
            this.id = id
        }

        override fun read(): NewsData.Base = value[id]
    }
}