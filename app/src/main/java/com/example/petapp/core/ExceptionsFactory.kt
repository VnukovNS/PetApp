package com.example.petapp.core

import com.example.petapp.domain.NoConnectionException
import com.example.petapp.domain.ServiceUnavailableException

interface ExceptionsFactory {
    fun handle(e: Exception): String

    class Base(private val resources: ManageResources = ManageResources.Base()) :
        ExceptionsFactory {
        override fun handle(e: Exception): String = when (e) {
            is NoConnectionException -> Failures.NoConnection(resources).getMessage()
            is ServiceUnavailableException -> Failures.ServiceUnavailable(resources).getMessage()
            else -> Failures.DefaultError(resources).getMessage()
        }
    }

}