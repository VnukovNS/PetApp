package com.example.petapp.newsListScreen.domain

import com.example.petapp.core.data.cloud.coil.ImageDownload
import com.example.petapp.core.data.cloud.errors.ExceptionsFactory
import com.example.petapp.newsListScreen.data.cache.NewsList
import com.example.petapp.newsListScreen.NewsUi
import com.example.petapp.newsListScreen.data.cloud.ImageUrlMapper
import com.example.petapp.newsListScreen.data.repository.NewsRepository

interface NewsInteractor {
    suspend fun getNews(category: String): NewsUi

    class Base(
        private val repository: NewsRepository,
        private val failureHandler: ExceptionsFactory,
        private val newsList: NewsList.Mutable,
        private val imageDownload: ImageDownload
    ) : NewsInteractor {

        override suspend fun getNews(category: String): NewsUi {
            return try {
                val result = repository.getNews(category)
                val resultUi =
                    result.map {
                        it.map(
                            // todo подумать как убрать мапперы в аргументы, чтобы было тестируемо
                            NewsDataNewsPreviewUiMapper(
                                //todo подумать как ускорить, мб создать кучу корутин
                                imageDownload.getImage(
                                    it.map(ImageUrlMapper())
                                )
                            )
                        )
                    }
                newsList.save(result, resultUi.map { it.icon() })
                // по факту что-то типа temp cache

                NewsUi.Initial(resultUi)
            } catch (e: Exception) {
                val message = failureHandler.handle(e)
                NewsUi.Error(message)
            }
        }
    }
}
