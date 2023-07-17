package com.example.petapp.detailnewsScreen.presentation

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.example.petapp.core.presentation.coil.ImageDownloadResult

interface DetailsNewsUi {

    fun map(
        titleView: TextView,
        imageView: ImageView,
        contentView: TextView,
        authorView: TextView,
        dateView: TextView,
        readMoreView: TextView
    )

    fun showProgressBar(progress: ProgressBar)

    abstract class Abstract(
        protected val title: String,
        protected val image: ImageDownloadResult,
        protected val content: String,
        protected val author: String,
        protected val date: String,
        protected val readMore: String
    ) : DetailsNewsUi {
        protected abstract val progressVisibility: Int

        override fun map(
            titleView: TextView,
            imageView: ImageView,
            contentView: TextView,
            authorView: TextView,
            dateView: TextView,
            readMoreView: TextView
        ) {
            titleView.text = title
            image.showImage(imageView)
            contentView.text = content
            authorView.text = author
            dateView.text = date
            readMoreView.text = readMore
        }

        override fun showProgressBar(progress: ProgressBar) {
            progress.visibility = progressVisibility
        }
    }

    object Loading : DetailsNewsUi {
        override fun map(
            titleView: TextView,
            imageView: ImageView,
            contentView: TextView,
            authorView: TextView,
            dateView: TextView,
            readMoreView: TextView
        ) = Unit

        override fun showProgressBar(progress: ProgressBar) {
            progress.visibility = View.VISIBLE
        }

    }
    class Initial(
        title: String,
        image: ImageDownloadResult,
        content: String,
        author: String,
        date: String,
        readMore: String
    ) : Abstract(
        title, image, content, author,
        date,
        readMore
    ) {
        override val progressVisibility = View.GONE
    }
}
