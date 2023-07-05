package com.example.petapp.newsListScreen.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.petapp.chooseCategoryScreen.data.ChooseCategory
import com.example.petapp.core.BaseViewModel
import com.example.petapp.core.presentation.Communication
import com.example.petapp.core.presentation.Navigate
import com.example.petapp.core.presentation.NavigationCommunication
import com.example.petapp.core.presentation.Screen
import com.example.petapp.newsListScreen.domain.NewsInteractor
import com.example.petapp.newsListScreen.data.cache.NewsList
import com.example.petapp.newsListScreen.NewsUi
import com.example.petapp.newsListScreen.NewsUiErrorMapper

interface NewsListViewModel : Navigate {

    class Base(
        private val interactor: NewsInteractor,
        private val communication: Communication.Mutable<NewsUi>,
        private val errorCommunication: ErrorCommunication,
        private val errorMapper: NewsUiErrorMapper,
        private val data: ChooseCategory.Mutable,
        private val navigationCommunication: NavigationCommunication.Mutable,
        private val newsListData: NewsList.Mutable
    ) :
        BaseViewModel(), Communication.Observe<NewsUi>, NewsListViewModel {

        fun init(isFirstInit: Boolean) {
            if (isFirstInit) {
                // мб делать хэндл в интеракторе?
                handle({ interactor.getNews(data.read()) }, { result ->
                    if (result is NewsUi.Initial) {
                        communication.map(result)
                        // todo потом добавить битмапу (глайд)
                    } else if (result is NewsUi.Error) {
                        communication.map(result)
                        errorCommunication.map(result.map(errorMapper))
                    }
                })
            }
        }

        override fun observe(owner: LifecycleOwner, observer: Observer<NewsUi>) =
            communication.observe(owner, observer)

        fun observeError(owner: LifecycleOwner, observer: Observer<String>) =
            errorCommunication.observe(owner, observer)

        fun saveNewsDataId(id: Int) {
            newsListData.saveId(id)
        }

        override fun navigate(screen: Screen) {
            navigationCommunication.map(screen)
        }
    }
}
