package com.example.petapp.core.di

import com.example.petapp.core.DispatchersList
import com.example.petapp.core.ManageResources
import org.koin.dsl.module

val appModule = module {
    single<ManageResources> {
        ManageResources.Base(context = get())
    }

    factory<DispatchersList> {
        DispatchersList.Base()
    }
}