package com.example.petapp.newsListScreen.presentation

import com.example.petapp.BaseTest
import com.example.petapp.chooseCategoryScreen.data.ChooseCategory
import com.example.petapp.core.presentation.NavigationCommunication
import com.example.petapp.core.presentation.Screen
import com.example.petapp.detailnewsScreen.presentation.DetailNewsScreen
import com.example.petapp.newsListScreen.NewsUi
import com.example.petapp.newsListScreen.NewsUiErrorMapper
import com.example.petapp.newsListScreen.domain.NewsInteractor
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class NewsListViewModelTest : BaseTest() {

    private lateinit var interactor: FakeNewsInteractor
    private lateinit var communication: FakeNewsListCommunication
    private lateinit var errorCommunication: FakeErrorCommunication
    private lateinit var data: FakeChooseCategory
    private lateinit var navigation: FakeNavigation
    private lateinit var newsListData: FakeNewsList
    private lateinit var viewModel: NewsListViewModel

    @Before
    fun setup() {
        interactor = FakeNewsInteractor.Base(functionsCallsStack)
        communication = FakeNewsListCommunication.Base(functionsCallsStack)
        errorCommunication = FakeErrorCommunication.Base(functionsCallsStack)
        data = FakeChooseCategory.Base(functionsCallsStack)
        navigation = FakeNavigation.Base(functionsCallsStack)
        newsListData = FakeNewsList.Base(functionsCallsStack)
        viewModel = NewsListViewModel.Base(
            interactor = interactor,
            communication = communication,
            errorCommunication = errorCommunication,
            errorMapper = NewsUiErrorMapper(),
            data = data,
            navigationCommunication = navigation,
            newsListData = newsListData,
            dispatchers = TestDispatchersList()
        )
    }

    @Test
    fun `test first run initial success`() = runBlocking {
        interactor.changeExpected(NewsUi.Initial(emptyList()))
        data.save("test") // подумать как убрать, сейчас впустую тестируем
        viewModel.init(true)
        communication.same(NewsUi.Loading)
        data.same("test")
        interactor.same(NewsUi.Initial(emptyList()))
        functionsCallsStack.checkStack(4)
    }

    @Test
    fun `test first run initial error`() = runBlocking {
        interactor.changeExpected(NewsUi.Error("error"))
        data.save("test") // подумать как убрать, сейчас впустую тестируем
        viewModel.init(true)
        communication.same(NewsUi.Loading)
        data.same("test")
        interactor.same(NewsUi.Error("error"))
        communication.same(NewsUi.Error("error"))
        errorCommunication.same("error")
        functionsCallsStack.checkStack(5)
    }

    @Test
    fun `test not first run initial success`() = runBlocking {
        interactor.changeExpected(NewsUi.Initial(emptyList()))
        data.save("test") // подумать как убрать, сейчас впустую тестируем
        viewModel.init(true)
        communication.same(NewsUi.Loading)
        data.same("test")
        interactor.same(NewsUi.Initial(emptyList()))
        viewModel.init(false)
        communication.same(NewsUi.Initial(emptyList()))
        functionsCallsStack.checkStack(4)
    }

    @Test
    fun `test not first run initial error`() = runBlocking {
        interactor.changeExpected(NewsUi.Error("error"))
        data.save("test") // подумать как убрать, сейчас впустую тестируем
        viewModel.init(true)
        communication.same(NewsUi.Loading)
        data.same("test")
        interactor.same(NewsUi.Error("error"))
        communication.same(NewsUi.Error("error"))
        errorCommunication.same("error")
        viewModel.init(false)
        // ничего не происходит, поскольку сейчас просто вываливается тоаст и потом пустой экран при повороте
        // нет стейта
        functionsCallsStack.checkStack(5)
    }

    @Test
    fun `navigate to screen`() {
        viewModel.navigate(DetailNewsScreen)
        navigation.same(DetailNewsScreen)
        functionsCallsStack.checkStack(1)
    }

    @Test
    fun `save id`() {
        viewModel.saveNewsDataId(123)
        newsListData.checkSaveIdCalled()
        newsListData.sameSaveId(123)
        functionsCallsStack.checkStack(1)
    }

    private interface FakeNavigation : NavigationCommunication.Mutable {

        fun same(other: Screen)

        class Base(private val functionCallsStack: FunctionsCallsStack) : FakeNavigation {
            private lateinit var screen: Screen

            override fun same(other: Screen) {
                assertEquals(screen, other)
                functionCallsStack.checkCalled(NAVIGATION_CALLED)
            }

            override fun map(source: Screen) {
                functionCallsStack.put(NAVIGATION_CALLED)
                screen = source
            }

            companion object {
                private const val NAVIGATION_CALLED = "navigation#map"
            }
        }
    }

    private interface FakeNewsListCommunication : NewsListCommunication {

        fun same(other: NewsUi)

        class Base(private val functionCallsStack: FunctionsCallsStack) :
            FakeNewsListCommunication {
            private val list = mutableListOf<NewsUi>()
            private var index = 0

            override fun map(source: NewsUi) {
                functionCallsStack.put(COMMUNICATION_CALLED)
                list.add(source)
            }

            override fun same(other: NewsUi) {
                assertEquals(list[index++], other)
                functionCallsStack.checkCalled(COMMUNICATION_CALLED)
            }
        }

        companion object {
            private const val COMMUNICATION_CALLED = "communication#map"
        }
    }

    private interface FakeNewsInteractor : NewsInteractor {

        fun same(other: NewsUi)

        fun changeExpected(expected: NewsUi)

        class Base(private val functionCallsStack: FunctionsCallsStack) : FakeNewsInteractor {

            private lateinit var result: NewsUi

            override fun same(other: NewsUi) {
                assertEquals(result, other)
                functionCallsStack.checkCalled(INTERACTOR_CALLED)
            }

            override fun changeExpected(expected: NewsUi) {
                result = expected
            }

            override suspend fun getNews(category: String): NewsUi {
                functionCallsStack.put(INTERACTOR_CALLED)
                return result
            }
        }

        companion object {
            private const val INTERACTOR_CALLED = "interactor#init"
        }
    }

    private interface FakeErrorCommunication : ErrorCommunication {

        fun same(other: String)

        class Base(private val functionCallsStack: FunctionsCallsStack) : FakeErrorCommunication {
            private val list = mutableListOf<String>()
            private var index = 0

            override fun map(source: String) {
                functionCallsStack.put(ERROR_COMMUNICATION_CALLED)
                list.add(source)
            }

            override fun same(other: String) {
                assertEquals(list[index++], other)
                functionCallsStack.checkCalled(ERROR_COMMUNICATION_CALLED)
            }
        }

        companion object {
            private const val ERROR_COMMUNICATION_CALLED = "errorcommunication#map"
        }
    }

    private interface FakeChooseCategory : ChooseCategory.Mutable {

        fun same(other: String)

        class Base(private val functionCallsStack: FunctionsCallsStack) : FakeChooseCategory {

            private var testString = "testString"

            override fun same(other: String) {
                assertEquals(testString, other)
                functionCallsStack.checkCalled(CHOOSE_CATEGORY_READ_CALLED)
            }

            override fun save(data: String) {
                testString = data
            }

            override fun read(): String {
                functionCallsStack.put(CHOOSE_CATEGORY_READ_CALLED)
                return testString
            }
        }

        companion object {
            private const val CHOOSE_CATEGORY_READ_CALLED = "choosecategory#read"
        }
    }
}