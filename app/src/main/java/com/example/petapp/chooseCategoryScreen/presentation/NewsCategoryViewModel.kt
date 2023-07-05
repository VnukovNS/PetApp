package com.example.petapp.chooseCategoryScreen.presentation

import com.example.petapp.chooseCategoryScreen.data.ChooseCategory
import com.example.petapp.core.BaseViewModel
import com.example.petapp.core.presentation.Navigate
import com.example.petapp.core.presentation.NavigationCommunication
import com.example.petapp.core.presentation.Screen

interface NewsCategoryViewModel : Navigate {

    class Base(private val navigation: NavigationCommunication.Mutable,
    private val choosenCategory: ChooseCategory.Mutable
    ) : BaseViewModel(), NewsCategoryViewModel {
        override fun navigate(screen: Screen) {
            navigation.map(screen)
        }
        fun chooseCategory(category: String) {
            choosenCategory.save(category)
        }
    }
}
