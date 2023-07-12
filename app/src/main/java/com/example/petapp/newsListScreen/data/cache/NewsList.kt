package com.example.petapp.newsListScreen.data.cache

import com.example.petapp.newsListScreen.data.cloud.NewsDataCloud
import com.example.petapp.core.presentation.coil.ImageDownloadResult

interface NewsList {

    interface Mutable {

        fun save(data: List<NewsDataCloud.Base>, customImageResultList: List<ImageDownloadResult>)

        fun saveId(id: Int)

        fun read(): NewsDataCloud.Base

        fun readImage(): ImageDownloadResult
    }

    class Base : Mutable {
        private var value: List<NewsDataCloud.Base> = listOf()
        private var imageList: List<ImageDownloadResult> = listOf()
        private var id: Int = -1

        override fun save(
            data: List<NewsDataCloud.Base>,
            customImageResultList: List<ImageDownloadResult>
        ) {
            value = data
            imageList = customImageResultList
        }

        override fun saveId(id: Int) {
            this.id = id
        }

        override fun read(): NewsDataCloud.Base = value[id]

        override fun readImage(): ImageDownloadResult = imageList[id]

    }
}
