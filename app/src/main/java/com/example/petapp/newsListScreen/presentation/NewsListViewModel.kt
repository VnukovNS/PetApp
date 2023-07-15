package com.example.petapp.newsListScreen.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.petapp.chooseCategoryScreen.data.ChooseCategory
import com.example.petapp.core.BaseViewModel
import com.example.petapp.core.presentation.*
import com.example.petapp.newsListScreen.domain.NewsInteractor
import com.example.petapp.newsListScreen.data.cache.NewsList
import com.example.petapp.newsListScreen.NewsUi
import com.example.petapp.newsListScreen.NewsUiErrorMapper

interface NewsListViewModel : Navigate {

    fun observeError(owner: LifecycleOwner, observer: Observer<String>)

    fun saveNewsDataId(id: Int)

    fun init(isFirstInit: Boolean)

    class Base(
        private val interactor: NewsInteractor,
        private val communication: NewsListCommunication,
        private val errorCommunication: ErrorCommunication,
        private val errorMapper: NewsUiErrorMapper,
        private val data: ChooseCategory.Mutable,
        private val navigationCommunication: NavigationCommunication.Mutable,
        private val newsListData: NewsList.Mutable,
        dispatchers: DispatchersList
    ) :
        BaseViewModel(dispatchers), Communication.Observe<NewsUi>, NewsListViewModel {

        override fun init(isFirstInit: Boolean) {
            if (isFirstInit) {
                communication.map(NewsUi.Loading)
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

        override fun observeError(owner: LifecycleOwner, observer: Observer<String>) =
            errorCommunication.observe(owner, observer)

        override fun saveNewsDataId(id: Int) {
            newsListData.saveId(id)
        }

        override fun navigate(screen: Screen) {
            navigationCommunication.map(screen)
        }
    }
}
