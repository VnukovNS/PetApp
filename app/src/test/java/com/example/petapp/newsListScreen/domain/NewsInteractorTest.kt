package com.example.petapp.newsListScreen.domain

import android.widget.ImageButton
import android.widget.ImageView
import com.example.petapp.BaseTest
import com.example.petapp.core.data.cloud.coil.ImageDownload
import com.example.petapp.core.data.cloud.errors.ExceptionsFactory
import com.example.petapp.core.presentation.coil.ImageDownloadResult
import com.example.petapp.newsListScreen.NewsUi
import com.example.petapp.newsListScreen.data.cloud.NewsDataCloud
import com.example.petapp.newsListScreen.data.repository.NewsRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class NewsInteractorTest : BaseTest() {
    private lateinit var repository: FakeRepository
    private lateinit var failureHandler: FakeFailureHandler
    private lateinit var newsList: FakeNewsList
    private lateinit var imageDownload: FakeImageDownload
    private lateinit var interactor: NewsInteractor

    @Before
    fun setup() {
        repository = FakeRepository.Base(functionsCallsStack)
        newsList = FakeNewsList.Base(functionsCallsStack)
        failureHandler = FakeFailureHandler.Base(functionsCallsStack)
        imageDownload = FakeImageDownload.Base(functionsCallsStack)
        interactor = NewsInteractor.Base(repository, failureHandler, newsList, imageDownload)
    }

    @Test
    fun `success get news and image`() = runBlocking {
        imageDownload.setSuccess(true)
        repository.setSuccess(true)
        val resultRepo = listOf(NewsDataCloud.Base())
        val resultImage = FakeImageDownload.FakeSuccess
        val result = interactor.getNews("")
        repository.sameNews(resultRepo)
        imageDownload.sameImage(resultImage)
        newsList.sameSave(resultRepo, listOf(resultImage))
        newsList.checkSaveCalled()
        val actual = result is NewsUi.Initial
        val expected = true
        assertEquals(expected, actual)
        // пока можем протестировать только так, поскольку маппер отдает другой объект, если его вызвать
        //todo refactor mapper
        functionsCallsStack.checkStack(3)
    }

    @Test
    fun `success get news and failed image`() = runBlocking {
        imageDownload.setSuccess(false)
        repository.setSuccess(true)
        val resultRepo = listOf(NewsDataCloud.Base())
        val resultImage = ImageDownloadResult.Error
        val result = interactor.getNews("")
        repository.sameNews(resultRepo)
        imageDownload.sameImage(resultImage)
        newsList.sameSave(resultRepo, listOf(resultImage))
        newsList.checkSaveCalled()
        val actual = result is NewsUi.Initial
        val expected = true
        assertEquals(expected, actual)
        functionsCallsStack.checkStack(3)
    }

    @Test
    fun `failed get news`() = runBlocking {
        imageDownload.setSuccess(true)
        repository.setSuccess(false)
        val actual = interactor.getNews("")
        repository.checkGetNewsCalled()
        failureHandler.checkHandleCalled()
        val expected = NewsUi.Error("fake exception")
        assertEquals(expected, actual)
        functionsCallsStack.checkStack(2)
    }

    private interface FakeRepository : NewsRepository {

        fun checkGetNewsCalled()

        fun sameNews(list: List<NewsDataCloud.Base>)

        fun setSuccess(success: Boolean)

        class Base(private val functionCallsStack: FunctionsCallsStack) : FakeRepository {

            private var newsList = emptyList<NewsDataCloud.Base>()
            private var success = true

            override fun checkGetNewsCalled() {
                functionCallsStack.checkCalled(GET_NEWS_CALLED)
            }

            override fun sameNews(list: List<NewsDataCloud.Base>) {
                assertEquals(newsList, list)
                functionCallsStack.checkCalled(GET_NEWS_CALLED)
            }

            override fun setSuccess(success: Boolean) {
                this.success = success
            }

            override suspend fun getNews(category: String): List<NewsDataCloud.Base> {
                functionCallsStack.put(GET_NEWS_CALLED)
                if (success) {
                    newsList = listOf(NewsDataCloud.Base())
                    return newsList
                }
                throw FakeException()
            }

            companion object {
                private val GET_NEWS_CALLED = "getNews"
            }
        }
    }

    private interface FakeFailureHandler : ExceptionsFactory {

        fun checkHandleCalled()

        class Base(private val functionCallsStack: FunctionsCallsStack) : FakeFailureHandler {

            override fun checkHandleCalled() {
                functionCallsStack.checkCalled(HANDLE_CALLED)
            }

            override fun handle(e: Exception): String {
                functionCallsStack.put(HANDLE_CALLED)
                if (e is FakeException) return "fake exception"
                throw java.lang.IllegalStateException("unknown exception type $e")
            }
        }

        companion object {
            const val HANDLE_CALLED = "handleCalled"
        }
    }

    private interface FakeImageDownload : ImageDownload {

        fun sameImage(other: ImageDownloadResult)

        fun setSuccess(success: Boolean)

        class Base(private val functionCallsStack: FunctionsCallsStack) : FakeImageDownload {

            private lateinit var image: ImageDownloadResult

            override fun sameImage(other: ImageDownloadResult) {
                assertEquals(image, other)
                functionCallsStack.checkCalled(GET_IMAGE_CALLED)
            }

            override fun setSuccess(success: Boolean) {
                image = if (success)
                    FakeSuccess
                else ImageDownloadResult.Error
            }

            override suspend fun getImage(url: String): ImageDownloadResult {
                functionCallsStack.put(GET_IMAGE_CALLED)
                return image
            }
        }

        object FakeSuccess : ImageDownloadResult {
            override fun showButton(imageButton: ImageButton) = Unit

            override fun showImage(imageView: ImageView) = Unit
        }

        companion object {
            private const val GET_IMAGE_CALLED = "getimage"
        }
    }

    private class FakeException : Exception()
}
