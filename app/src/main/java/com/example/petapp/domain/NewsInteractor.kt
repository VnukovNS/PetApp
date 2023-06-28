package com.example.petapp.domain

import com.example.petapp.core.ExceptionsFactory
import com.example.petapp.core.cloud.ProvideConverterFactory
import com.example.petapp.core.cloud.ProvideLoggingInterceptor
import com.example.petapp.core.cloud.ProvideOkHttpClientBuilder
import com.example.petapp.core.cloud.ProvideRetrofitBuilder
import com.example.petapp.data.NewsCloudDataSource
import com.example.petapp.data.NewsResult
import com.example.petapp.data.news.NewsMakeService
import com.example.petapp.data.news.NewsService

interface NewsInteractor {
    suspend fun getNews(category: String): NewsResult

    class Base(
        private val repository: NewsRepository = NewsRepository.Base(
            NewsCloudDataSource(
                NewsMakeService(
                    ProvideRetrofitBuilder.Base(
                        ProvideConverterFactory.Base(),
                        ProvideOkHttpClientBuilder.AddInterceptor(
                            ProvideLoggingInterceptor.Debug(),
                            ProvideOkHttpClientBuilder.Base()
                        )
                    )
                ).service(NewsService::class.java)
            )
        ),
        private val failureHandler: ExceptionsFactory.Base = ExceptionsFactory.Base()
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