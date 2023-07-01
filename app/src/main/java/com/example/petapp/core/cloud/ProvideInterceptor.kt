package com.example.petapp.core.cloud

import okhttp3.Interceptor

interface ProvideInterceptor {

    fun interceptor(): Interceptor
}
