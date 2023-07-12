package com.example.petapp.newsListScreen.data.repository

import com.example.petapp.core.presentation.DispatchersList
import com.example.petapp.newsListScreen.data.cloud.NewsCloudDataSource
import com.example.petapp.newsListScreen.data.cloud.NewsDataCloud
import kotlinx.coroutines.withContext

interface NewsRepository {

    suspend fun getNews(category: String): List<NewsDataCloud.Base>

    // todo по идее здесь сделаем выбор между тем, откуда берем данные - из кэша или из клауда

    class Base(
        private val cloudDataSource: NewsCloudDataSource,
        private val dispatchers: DispatchersList
    ) : NewsRepository {
        override suspend fun getNews(category: String): List<NewsDataCloud.Base> = withContext(dispatchers.io()) {
            try {
                val result = cloudDataSource.getNews(category)
                return@withContext result
            } catch (e: Exception) {
                throw e
            }
        }
    }
}
