package com.example.petapp.detailnewsScreen.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.petapp.core.BaseViewModel
import com.example.petapp.core.presentation.Init
import com.example.petapp.core.presentation.Communication
import com.example.petapp.newsListScreen.data.cloud.NewsData
import com.example.petapp.newsListScreen.data.cache.NewsList

interface DetailNewsViewModel {

    class Base(
        private val data: NewsList.Mutable,
        private val communication: Communication.Mutable<DetailsNewsUi>,
        private val mapper: NewsData.Mapper<DetailsNewsUi>
    ) : BaseViewModel(), Init, Communication.Observe<DetailsNewsUi> {
        override fun init() {
            communication.map(data.read().map(mapper))
        }

        override fun observe(owner: LifecycleOwner, observer: Observer<DetailsNewsUi>) {
            communication.observe(owner, observer)
        }
    }
}
