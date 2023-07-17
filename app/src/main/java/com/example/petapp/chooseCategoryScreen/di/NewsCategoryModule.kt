package com.example.petapp.chooseCategoryScreen

import com.example.petapp.chooseCategoryScreen.presentation.NewsCategoryCommunication
import com.example.petapp.chooseCategoryScreen.presentation.NewsCategoryViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val newsCategoryModule = module {

    viewModel<NewsCategoryViewModel.Base> {
        NewsCategoryViewModel.Base(
            navigation = get(),
            choosenCategory = get(),
            communication = get(),
            dispatchers = get()
        )
    }

    single<NewsCategoryCommunication> {
        NewsCategoryCommunication.Base()
    }
}