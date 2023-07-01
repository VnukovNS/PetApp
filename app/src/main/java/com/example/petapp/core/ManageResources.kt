package com.example.petapp.core

import android.content.Context
import androidx.annotation.StringRes

interface ManageResources {

    fun string(@StringRes id: Int): String

    class Base(private val context: Context = App.getInstance()) : ManageResources {
        override fun string(id: Int): String = context.getString(id)
    }
}