package com.example.petapp.detailnewsScreen.presentation

import android.view.View
import android.widget.ProgressBar
import android.widget.TextView

interface DetailsNewsUi {

    //    fun map(titleView: TextView, imageView: ImageView, contentView: TextView, authorView: TextView, dateView: TextView)
    fun map(
        progress: ProgressBar,
        titleView: TextView,
        contentView: TextView,
        authorView: TextView,
        dateView: TextView,
        readMoreView: TextView
    )

    abstract class Abstract(
        protected val title: String,
        protected val content: String,
        protected val author: String,
        protected val date: String,
        protected val readMore: String
    ) : DetailsNewsUi {
        protected abstract val progressVisibility : Int

        override fun map(
            progress: ProgressBar,
            titleView: TextView,
            contentView: TextView,
            authorView: TextView,
            dateView: TextView,
            readMoreView: TextView
        ) {
            titleView.text = title
            contentView.text = content
            authorView.text = author
            dateView.text = date
            readMoreView.text = readMore
            progress.visibility = progressVisibility
        }
    }


    class Initial(title: String, content: String, author: String, date: String, readMore: String) : Abstract(title, content, author,
        date,
        readMore
    ) {
        override val progressVisibility = View.GONE
    }
}