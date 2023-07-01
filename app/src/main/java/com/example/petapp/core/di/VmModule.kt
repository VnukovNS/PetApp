package com.example.petapp.core.di

import com.example.petapp.MainActivityViewModel
import com.example.petapp.firstScreen.MainFragmentViewModel
import com.example.petapp.secondScreen.SecondFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val vmModule = module {

    viewModel<SecondFragmentViewModel> {
        SecondFragmentViewModel(interactor = get(), communication = get())
    }

    viewModel<MainFragmentViewModel> {
        MainFragmentViewModel()
    }

    viewModel<MainActivityViewModel> {
        MainActivityViewModel()
    }
}
