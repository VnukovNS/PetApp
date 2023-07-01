package com.example.petapp.secondScreen

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.petapp.core.BaseViewModel
import com.example.petapp.core.presentation.Communication
import com.example.petapp.core.Init
import com.example.petapp.data.NewsResult
import com.example.petapp.domain.NewsInteractor


class SecondFragmentViewModel(
    private val interactor: NewsInteractor,
    private val communication: Communication.Mutable<NewsUi>
) :
    BaseViewModel(), Init, Communication.Observe<NewsUi> {

    override fun init() {
//        handle({interactor.getNews("automobile")}, {
//            // todo init recyclerAdapter
//        })
    }

    fun getNews() = handle({ interactor.getNews("automobile") }, {
        if (it is NewsResult.Success) {
            // todo сделать еще один маппинг
            val ui: MutableList<NewsPreviewUi> = mutableListOf()
            for (newsData in it.news) {
                // todo потом добавить битмапу (глайд)
                ui.add(NewsPreviewUi.Base(newsData.title, newsData.content))
            }
            communication.map(NewsUi.Initial(ui))
        } else if (it is NewsResult.Error) {
            Log.e("vniks", "error: ${it.message}")
        }
    })

    override fun observe(owner: LifecycleOwner, observer: Observer<NewsUi>) =
        communication.observe(owner, observer)
}
