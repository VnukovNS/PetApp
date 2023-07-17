package com.example.petapp.detailnewsScreen.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.petapp.core.BaseViewModel
import com.example.petapp.core.presentation.Init
import com.example.petapp.core.presentation.Communication
import com.example.petapp.core.presentation.DispatchersList
import com.example.petapp.detailnewsScreen.domain.DetailsNewsUiMapper
import com.example.petapp.newsListScreen.data.cache.NewsList

interface DetailNewsViewModel {

    class Base(
        private val data: NewsList.Mutable,
        private val communication: NewsDetailCommunication,
        dispatchers: DispatchersList
    ) : BaseViewModel(dispatchers), Init, Communication.Observe<DetailsNewsUi> {
        override fun init() {
            communication.map(DetailsNewsUi.Loading)
            communication.map(data.read().map(DetailsNewsUiMapper(data.readImage())))
        }

        override fun observe(owner: LifecycleOwner, observer: Observer<DetailsNewsUi>) {
            communication.observe(owner, observer)
        }
    }
}
