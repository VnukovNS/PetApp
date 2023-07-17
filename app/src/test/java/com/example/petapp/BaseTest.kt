package com.example.petapp

import com.example.petapp.core.presentation.DispatchersList
import com.example.petapp.core.presentation.coil.ImageDownloadResult
import com.example.petapp.newsListScreen.data.cache.NewsList
import com.example.petapp.newsListScreen.data.cloud.NewsDataCloud
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.Assert
import org.junit.Before

abstract class BaseTest {
    protected lateinit var functionsCallsStack: FunctionsCallsStack

    @Before
    fun init() {
        functionsCallsStack = FunctionsCallsStack.Base()
    }

    protected interface FunctionsCallsStack {

        fun put(funName: String)
        fun checkCalled(funName: String)
        fun checkStack(size: Int)

        class Base : FunctionsCallsStack {
            private val list = mutableListOf<String>()
            private var count = 0

            override fun put(funName: String) {
                list.add(funName)
            }

            override fun checkCalled(funName: String) {
                Assert.assertEquals(funName, list[count++])
            }

            override fun checkStack(size: Int) {
                Assert.assertEquals(size, list.size)
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    protected class TestDispatchersList(
        private val dispatcher: CoroutineDispatcher = UnconfinedTestDispatcher()
    ) : DispatchersList {
        override fun io(): CoroutineDispatcher = dispatcher

        override fun ui(): CoroutineDispatcher = dispatcher
    }

    protected interface FakeNewsList : NewsList.Mutable {
        fun sameSave(
            data: List<NewsDataCloud.Base>,
            customImageResultList: List<ImageDownloadResult>
        )

        fun sameSaveId(id: Int)

        fun checkSaveCalled()

        fun checkSaveIdCalled()

        fun checkReadCalled()

        fun checkReadImageCalled()
        class Base(private val functionCallsStack: FunctionsCallsStack) : FakeNewsList {
            private var testId = -1
            private lateinit var listData: List<NewsDataCloud.Base>
            private lateinit var listImages: List<ImageDownloadResult>
            private var index = 0

            override fun sameSave(
                data: List<NewsDataCloud.Base>,
                customImageResultList: List<ImageDownloadResult>
            ) {
                Assert.assertEquals(listData, data)
                Assert.assertEquals(listImages, customImageResultList)

            }

            override fun sameSaveId(id: Int) = Assert.assertEquals(testId, id)

            override fun checkSaveCalled() = functionCallsStack.checkCalled(SAVE_CALLED)

            override fun checkSaveIdCalled() = functionCallsStack.checkCalled(SAVE_ID_CALLED)

            override fun checkReadCalled() = functionCallsStack.checkCalled(READ_CALLED)

            override fun checkReadImageCalled() = functionCallsStack.checkCalled(READ_IMAGE_CALLED)


            override fun save(
                data: List<NewsDataCloud.Base>,
                customImageResultList: List<ImageDownloadResult>
            ) {
                functionCallsStack.put(SAVE_CALLED)
                listData = data
                listImages = customImageResultList
            }

            override fun saveId(id: Int) {
                functionCallsStack.put(SAVE_ID_CALLED)
                testId = id
            }

            override fun read(): NewsDataCloud.Base {
                functionCallsStack.put(READ_CALLED)
                return listData[index]
            }

            override fun readImage(): ImageDownloadResult {
                functionCallsStack.put(READ_IMAGE_CALLED)
                return listImages[index]
            }
        }

        companion object {
            private const val READ_IMAGE_CALLED = "readImage"
            private const val READ_CALLED = "read"
            private const val SAVE_ID_CALLED = "saveid"
            private const val SAVE_CALLED = "save"
        }
    }

}