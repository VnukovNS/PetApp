package com.example.petapp.detailnewsScreen.presentation

import com.example.petapp.BaseTest
import com.example.petapp.core.presentation.coil.ImageDownloadResult
import com.example.petapp.newsListScreen.data.cloud.NewsDataCloud
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class DetailNewsViewModelTest : BaseTest() {

    private lateinit var communication: FakeNewsDetailCommunication
    private lateinit var data: FakeNewsList
    private lateinit var viewModel: DetailNewsViewModel.Base

    @Before
    fun setup() {
        communication = FakeNewsDetailCommunication.Base(functionsCallsStack)
        data = FakeNewsList.Base(functionsCallsStack)
        viewModel = DetailNewsViewModel.Base(data, communication, TestDispatchersList())
    }

    @Test
    fun `test init success`() {
        val id = 0
        val listData = listOf(NewsDataCloud.Base())
        val listImage = listOf(ImageDownloadResult.Error)
        data.save(listData, listImage)
        data.checkSaveCalled()
        data.saveId(id)
        data.checkSaveIdCalled()
        viewModel.init()
        communication.same(DetailsNewsUi.Loading)
        data.sameSave(listData, listImage)
        functionsCallsStack.checkStack(6)
    }

    private interface FakeNewsDetailCommunication : NewsDetailCommunication {
        fun same(other: DetailsNewsUi)

        class Base(private val functionCallsStack: FunctionsCallsStack) :
            FakeNewsDetailCommunication {

            private val list = mutableListOf<DetailsNewsUi>()
            private var index = 0

            override fun same(other: DetailsNewsUi) {
                assertEquals(list[index++], other)
                functionCallsStack.checkCalled(COMMUNICATION_CALLED)
            }

            override fun map(source: DetailsNewsUi) {
                functionCallsStack.put(COMMUNICATION_CALLED)
                list.add(source)
            }
        }

        companion object {
            private const val COMMUNICATION_CALLED = "communication#map"
        }
    }
}
