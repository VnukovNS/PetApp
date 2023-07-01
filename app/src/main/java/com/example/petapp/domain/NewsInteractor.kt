package com.example.petapp.domain

import com.example.petapp.core.ExceptionsFactory
import com.example.petapp.data.NewsResult

interface NewsInteractor {
    suspend fun getNews(category: String): NewsResult

    class Base(
        private val repository: NewsRepository,
        private val failureHandler: ExceptionsFactory
    ) : NewsInteractor {

        override suspend fun getNews(category: String): NewsResult {
            return try {
                //todo неплохо бы в вм отдавать уже готовые данные для ui, а не просто классы
                NewsResult.Success(repository.getNews(category))
            } catch (e: Exception) {
                val message = failureHandler.handle(e)
                NewsResult.Error(message)
            }
        }
    }
}
