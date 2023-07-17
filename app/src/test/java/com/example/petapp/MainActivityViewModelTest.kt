package com.example.petapp

import com.example.petapp.chooseCategoryScreen.presentation.FirstScreen
import com.example.petapp.core.presentation.NavigationCommunication
import com.example.petapp.core.presentation.Screen
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class MainActivityViewModelTest : BaseTest() {
    private lateinit var fakeCommunication: FakeCommunication
    private lateinit var viewModel: MainActivityViewModel

    @Before
    fun setup() {
        fakeCommunication = FakeCommunication.Base(functionsCallsStack)
        viewModel = MainActivityViewModel.Base(fakeCommunication, TestDispatchersList())
    }

    @Test
    fun `navigate to screen`() {
        viewModel.navigate(FirstScreen)
        fakeCommunication.checkMapCalled()
        fakeCommunication.assertScreenEmitted(FirstScreen)
        functionsCallsStack.checkStack(1)
    }



    private interface FakeCommunication : NavigationCommunication.Mutable {
        fun assertScreenEmitted(screen: Screen)

        fun checkMapCalled()

        class Base(private val functionsCallsStack: FunctionsCallsStack) : FakeCommunication {

            private var emittedScreen: Screen? = null

            override fun assertScreenEmitted(screen: Screen) {
                assertEquals(screen, emittedScreen)
            }

            override fun checkMapCalled() {
                functionsCallsStack.checkCalled(MAP_CALLED)
            }

            override fun map(source: Screen) {
                emittedScreen = source
                functionsCallsStack.put(MAP_CALLED)
            }

            companion object {

                private const val MAP_CALLED = "MAP_CALLED"
            }

        }
    }
}