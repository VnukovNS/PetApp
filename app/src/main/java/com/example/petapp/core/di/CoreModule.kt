package com.example.petapp.core

import com.example.petapp.core.data.cloud.ProvideConverterFactory
import com.example.petapp.core.data.cloud.ProvideLoggingInterceptor
import com.example.petapp.core.data.cloud.ProvideOkHttpClientBuilder
import com.example.petapp.core.data.cloud.ProvideRetrofitBuilder
import com.example.petapp.core.data.cloud.errors.ExceptionsFactory
import com.example.petapp.core.presentation.DispatchersList
import com.example.petapp.core.presentation.ManageResources
import org.koin.dsl.module

val coreModule = module {
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

    factory<DispatchersList> {
        DispatchersList.Base()
    }
}