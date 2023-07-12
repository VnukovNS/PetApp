package com.example.petapp.detailnewsScreen

import com.example.petapp.core.presentation.Communication
import com.example.petapp.detailnewsScreen.presentation.DetailNewsViewModel
import com.example.petapp.detailnewsScreen.presentation.DetailsNewsUi
import com.example.petapp.detailnewsScreen.presentation.NewsDetailCommunication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val detailNewsModule = module {
    viewModel<DetailNewsViewModel.Base> {
        DetailNewsViewModel.Base(data = get(), communication = get())
    }

    factory<Communication.Mutable<DetailsNewsUi>> {
        NewsDetailCommunication.Base()
    }
}