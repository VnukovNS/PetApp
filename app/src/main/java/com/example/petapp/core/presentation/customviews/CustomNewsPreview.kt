package com.example.petapp.core.presentation.customviews

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.example.petapp.R

class CustomNewsPreview : LinearLayout {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    init {
        inflate(context, R.layout.custom_news_preview, this)
    }

    //todo подумать надо ли что-то добавить, чтобы вью сама умела
}
