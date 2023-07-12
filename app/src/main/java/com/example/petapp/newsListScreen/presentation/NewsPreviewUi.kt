package com.example.petapp.newsListScreen.presentation

import android.widget.ImageButton
import android.widget.TextView
import com.example.petapp.core.presentation.coil.ImageDownloadResult

interface NewsPreviewUi {
    fun title(): String
    fun content(): String
    fun icon(): ImageDownloadResult
    fun map(titleView: TextView, contentView: TextView, imageButton: ImageButton)

    abstract class Abstract(
        protected open val title: String,
        protected open val content: String,
        protected open val customImageResult: ImageDownloadResult
    ) : NewsPreviewUi {

        override fun title(): String = title
        override fun content(): String = content
        override fun icon(): ImageDownloadResult = customImageResult

        override fun map(titleView: TextView, contentView: TextView, imageButton: ImageButton) {
            titleView.text = title
            contentView.text = content
            customImageResult.showButton(imageButton)
        }
    }

    // сделано, если потом будут состояния, чтобы можно было что-то дефолтно прописать
    class Base(
        override val title: String,
        override val content: String,
        override val customImageResult: ImageDownloadResult
    ) : Abstract(title, content, customImageResult)
}
