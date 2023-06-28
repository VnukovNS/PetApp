package com.example.petapp.domain

import com.example.petapp.core.DispatchersList
import com.example.petapp.data.NewsCloudDataSource
import com.example.petapp.data.NewsData
import kotlinx.coroutines.withContext

interface NewsRepository {

    suspend fun getNews(category: String): List<NewsData>

    // todo по идее здесь сделаем выбор между тем, откуда берем данные - из кэша или из клауда

    class Base(
        private val cloudDataSource: NewsCloudDataSource,
        private val dispatchers: DispatchersList = DispatchersList.Base()
    ) : NewsRepository {
        override suspend fun getNews(category: String): List<NewsData> = withContext(dispatchers.io()) {
            try {
                return@withContext cloudDataSource.getNews(category)
            } catch (e: Exception) {
                throw e
            }
        }
    }
}
