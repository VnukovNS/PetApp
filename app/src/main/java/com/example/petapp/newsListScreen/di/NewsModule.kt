package com.example.petapp.newsListScreen

import com.example.petapp.core.presentation.Communication
import com.example.petapp.newsListScreen.data.cloud.NewsCloudDataSource
import com.example.petapp.newsListScreen.data.cloud.NewsMakeService
import com.example.petapp.newsListScreen.data.cloud.NewsService
import com.example.petapp.newsListScreen.data.cloud.NewsCloudDataMapper
import com.example.petapp.newsListScreen.domain.NewsInteractor
import com.example.petapp.newsListScreen.data.repository.NewsRepository
import com.example.petapp.newsListScreen.presentation.ErrorCommunication
import com.example.petapp.newsListScreen.presentation.NewsListCommunication
import com.example.petapp.newsListScreen.presentation.NewsListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val newsModule = module {

    viewModel<NewsListViewModel.Base> {
        NewsListViewModel.Base(
            interactor = get(),
            communication = get(),
            errorCommunication = get(),
            errorMapper = get(),
            data = get(),
            navigationCommunication = get(),
            newsListData = get()
        )
    }

    factory<Communication.Mutable<NewsUi>> {
        NewsListCommunication.Base()
    }

    factory<ErrorCommunication> {
        ErrorCommunication.Base()
    }

    factory<NewsUiErrorMapper> {
        NewsUiErrorMapper()
    }

    factory<NewsMakeService> {
        NewsMakeService(retrofitBuilder = get())
    }

    factory<NewsCloudDataSource> {
        NewsCloudDataSource(
            service =
            NewsMakeService(retrofitBuilder = get()).service(NewsService::class.java),
            mapper = get()
        )
    }

    factory<NewsCloudDataMapper> {
        NewsCloudDataMapper()
    }

    factory<NewsRepository> {
        NewsRepository.Base(cloudDataSource = get(), dispatchers = get())
    }

    factory<NewsInteractor> {
        NewsInteractor.Base(repository = get(), failureHandler = get(), newsList = get(), imageDownload = get())
    }
}
