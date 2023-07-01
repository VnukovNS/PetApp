package com.example.petapp.core

import android.app.Application
import com.example.petapp.core.di.appModule
import com.example.petapp.core.di.domainModule
import com.example.petapp.core.di.vmModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@App)
            modules(listOf(appModule, domainModule, vmModule))
        }
    }
}
