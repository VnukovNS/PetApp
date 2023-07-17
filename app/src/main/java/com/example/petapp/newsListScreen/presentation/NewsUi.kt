package com.example.petapp.newsListScreen

import android.content.Context
import android.view.View
import android.widget.ProgressBar
import com.example.petapp.newsListScreen.presentation.NewsAdapter
import com.example.petapp.newsListScreen.presentation.NewsPreviewUi

interface NewsUi {

    fun map(adapter: NewsAdapter, context: Context)

    fun showProgressBar(progressBar: ProgressBar, context: Context)

    object Loading : NewsUi {
        override fun map(adapter: NewsAdapter, context: Context) = Unit

        override fun showProgressBar(progressBar: ProgressBar, context: Context) {
            progressBar.visibility = View.VISIBLE
        }

    }

    data class Initial(private val list: List<NewsPreviewUi>) : NewsUi {
        override fun map(adapter: NewsAdapter, context: Context) = adapter.map(list)
        override fun showProgressBar(progressBar: ProgressBar, context: Context) {
            progressBar.visibility = View.GONE
        }
    }

    data class Error(private val errorMessage: String) : NewsUi {
        override fun map(adapter: NewsAdapter, context: Context) = Unit
        fun <T> map(errorMapper: Mapper<T>): T = errorMapper.map(errorMessage)
        override fun showProgressBar(progressBar: ProgressBar, context: Context) {
            progressBar.visibility = View.GONE
        }
        // todo добавить базовое отображение ошибки, чтобы можно было повторить запрос

    }

    interface Mapper<T> {
        fun map(errorMessage: String): T
    }
}

class NewsUiErrorMapper : NewsUi.Mapper<String> {
    override fun map(errorMessage: String): String = errorMessage

}
