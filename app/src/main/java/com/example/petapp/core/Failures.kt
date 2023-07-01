package com.example.petapp.core

import androidx.annotation.StringRes
import com.example.petapp.R

interface Failures {
    fun getMessage(): String

    abstract class Abstract(private val resources: ManageResources) : Failures {
        @StringRes
        protected abstract fun getMessageResId(): Int

        override fun getMessage(): String = resources.string(getMessageResId())
    }

    class NoConnection(resources: ManageResources) : Abstract(resources) {
        override fun getMessageResId(): Int = R.string.no_connection
    }

    class ServiceUnavailable(resources: ManageResources) : Abstract(resources) {
        override fun getMessageResId(): Int = R.string.service_unavailable
    }

    class DefaultError(resources: ManageResources) : Abstract(resources) {
        override fun getMessageResId(): Int = R.string.default_error
    }
}
