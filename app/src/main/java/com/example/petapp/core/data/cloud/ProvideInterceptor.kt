package com.example.petapp.core.data.cloud

import okhttp3.Interceptor

interface ProvideInterceptor {

    fun interceptor(): Interceptor
}
