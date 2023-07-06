package com.example.petapp.chooseCategoryScreen.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.petapp.chooseCategoryScreen.data.ChooseCategory
import com.example.petapp.core.BaseViewModel
import com.example.petapp.core.presentation.*

interface NewsCategoryViewModel : Navigate {

    fun chooseCategory(category: String)

    class Base(
        private val navigation: NavigationCommunication.Mutable,
        private val choosenCategory: ChooseCategory.Mutable,
        private val communication: NewsCategoryCommunication
    ) : BaseViewModel(), NewsCategoryViewModel, Init, Communication.Observe<NewsCategoryUi> {

        override fun init() {
            communication.map(NewsCategoryUi.Initial())
        }

        override fun navigate(screen: Screen) {
            navigation.map(screen)
            communication.map(NewsCategoryUi.Loading())
        }

        override fun chooseCategory(category: String) {
            choosenCategory.save(category)
        }

        override fun observe(owner: LifecycleOwner, observer: Observer<NewsCategoryUi>) =
            communication.observe(owner, observer)
    }
}
