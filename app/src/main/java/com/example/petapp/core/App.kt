package com.example.petapp.core

import android.app.Application

class App : Application() {

    // todo по-хорошему надо убрать эту дичь и сделать через DI
    companion object {
        private var singleton: App? = null
        fun getInstance(): App = singleton!!
    }

    override fun onCreate() {
        super.onCreate()
        singleton = this
    }
}
