package com.example.petapp.core.di

import com.example.petapp.MainActivityViewModel
import com.example.petapp.chooseCategoryScreen.data.ChooseCategory
import com.example.petapp.core.presentation.NavigationCommunication
import com.example.petapp.newsListScreen.data.cache.NewsList
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel<MainActivityViewModel.Base> {
        MainActivityViewModel.Base(navigationCommunication = get())
    }

    single<ChooseCategory.Mutable> {
        ChooseCategory.Base()
    }

    /*todo подумать как сделать, чтобы отдавался один и тот же синглтон,
   на разные реализации типа NavigationCommunication.Mutable, NavigationCommunication.Update и
   NavigationCommunication.Observe*/
    single<NavigationCommunication.Mutable> {
        NavigationCommunication.Base()
    }

    single<NewsList.Mutable> {
        NewsList.Base()
    }
}