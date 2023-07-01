package com.example.petapp.core.di

import com.example.petapp.core.ExceptionsFactory
import com.example.petapp.core.cloud.ProvideConverterFactory
import com.example.petapp.core.cloud.ProvideLoggingInterceptor
import com.example.petapp.core.cloud.ProvideOkHttpClientBuilder
import com.example.petapp.core.cloud.ProvideRetrofitBuilder
import com.example.petapp.core.presentation.Communication
import com.example.petapp.data.NewsCloudDataSource
import com.example.petapp.data.news.NewsMakeService
import com.example.petapp.data.news.NewsService
import com.example.petapp.domain.NewsInteractor
import com.example.petapp.domain.NewsRepository
import com.example.petapp.secondScreen.NewsCommunication
import com.example.petapp.secondScreen.NewsUi
import org.koin.dsl.module

val domainModule = module {

    factory<ProvideOkHttpClientBuilder> {
        ProvideOkHttpClientBuilder.AddInterceptor(
            interceptor = ProvideLoggingInterceptor.Debug(),
            provideOkHttp = ProvideOkHttpClientBuilder.Base()
        )
    }

    factory<ProvideRetrofitBuilder> {
        ProvideRetrofitBuilder.Base(
            provideConverterFactory = ProvideConverterFactory.Base(),
            httpClientBuilder = get()
        )
    }

    factory<NewsMakeService> {
        NewsMakeService(retrofitBuilder = get())
    }

    factory<NewsCloudDataSource> {
        NewsCloudDataSource(
            service =
            NewsMakeService(retrofitBuilder = get()).service(NewsService::class.java)
        )
    }

    factory<NewsRepository> {
        NewsRepository.Base(cloudDataSource = get(), dispatchers = get())
    }

    factory<NewsInteractor> {
        NewsInteractor.Base(repository = get(), failureHandler = get())
    }

    factory<ExceptionsFactory> {
        ExceptionsFactory.Base(resources = get())
    }

    factory<Communication.Mutable<NewsUi>> {
        NewsCommunication.Base()
    }
}