package com.example.petapp.chooseCategoryScreen.presentation

import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.ProgressBar

interface NewsCategoryUi {

    fun blockButtons(button: Button, context: Context)

    fun showProgressBar(progressBar: ProgressBar, context: Context)

    abstract class Abstract : NewsCategoryUi {
        protected abstract var isButtonEnabled : Boolean
        protected abstract var progressBarVisibility: Int

        override fun blockButtons(button: Button, context: Context) {
            button.isEnabled = isButtonEnabled
        }

        override fun showProgressBar(progressBar: ProgressBar, context: Context) {
            progressBar.visibility = progressBarVisibility
        }
    }

    class Initial : Abstract() {

        override var isButtonEnabled: Boolean = true
        override var progressBarVisibility: Int = View.GONE
    }

    class Loading : Abstract() {

        override var isButtonEnabled: Boolean = false
        override var progressBarVisibility: Int = View.GONE
    }
}
