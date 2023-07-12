package com.example.petapp.core.presentation

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.appcompat.content.res.AppCompatResources

interface ManageResources {

    fun string(@StringRes id: Int): String

    fun drawable(@DrawableRes id: Int) : Drawable

    class Base(private val context: Context) : ManageResources {
        override fun string(id: Int): String = context.getString(id)

        override fun drawable(id: Int): Drawable = AppCompatResources.getDrawable(context, id)!!
    }
}
