package com.example.petapp.core

import coil.ImageLoader
import com.example.petapp.MainActivityViewModel
import com.example.petapp.chooseCategoryScreen.data.ChooseCategory
import com.example.petapp.core.data.cloud.ProvideConverterFactory
import com.example.petapp.core.data.cloud.ProvideLoggingInterceptor
import com.example.petapp.core.data.cloud.ProvideOkHttpClientBuilder
import com.example.petapp.core.data.cloud.ProvideRetrofitBuilder
import com.example.petapp.core.data.cloud.coil.ImageDownload
import com.example.petapp.core.data.cloud.coil.ProvideImageLoader
import com.example.petapp.core.data.cloud.errors.ExceptionsFactory
import com.example.petapp.core.presentation.DispatchersList
import com.example.petapp.core.presentation.ManageResources
import com.example.petapp.core.presentation.NavigationCommunication
import com.example.petapp.newsListScreen.data.cache.NewsList
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val coreModule = module {

    viewModel<MainActivityViewModel.Base> {
        MainActivityViewModel.Base(navigationCommunication = get(),
        dispatchers = get())
    }

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

    factory<ExceptionsFactory> {
        ExceptionsFactory.Base(resources = get())
    }

    single<ManageResources> {
        ManageResources.Base(context = get())
    }

    single<DispatchersList> {
        DispatchersList.Base()
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

    single<ImageLoader> {
        ProvideImageLoader.Base( context = get()).provideImageLoader()
    }

    factory<ImageDownload> {
        ImageDownload.Base(context = get(), imageLoader = get())
    }
}