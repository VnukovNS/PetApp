package com.example.petapp.core.data.cloud.errors

import com.example.petapp.core.presentation.ManageResources

interface ExceptionsFactory {
    fun handle(e: Exception): String

    class Base(private val resources: ManageResources) :
        ExceptionsFactory {
        override fun handle(e: Exception): String = when (e) {
            is NoConnectionException -> Failures.NoConnection(resources).getMessage()
            is ServiceUnavailableException -> Failures.ServiceUnavailable(resources).getMessage()
            else -> Failures.DefaultError(resources).getMessage()
        }
    }
}
