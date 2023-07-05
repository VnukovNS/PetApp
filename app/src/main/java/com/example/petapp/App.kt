package com.example.petapp

import android.app.Application
import com.example.petapp.chooseCategoryScreen.newsCategoryModule
import com.example.petapp.core.coreModule
import com.example.petapp.core.di.appModule
import com.example.petapp.detailnewsScreen.detailNewsModule
import com.example.petapp.newsListScreen.newsModule
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
            modules(
                listOf(
                    appModule,
                    newsModule,
                    detailNewsModule,
                    coreModule,
                    newsCategoryModule
                )
            )
        }
    }
}
