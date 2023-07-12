package com.example.petapp.core.presentation.customviews

import android.content.Context
import android.util.AttributeSet

class CustomImageView : androidx.appcompat.widget.AppCompatImageView, ImageContainer {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun showImage() {

    }
}

interface ImageContainer {
    fun showImage()
}
