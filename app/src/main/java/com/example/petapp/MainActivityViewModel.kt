package com.example.petapp

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.petapp.core.BaseViewModel
import com.example.petapp.core.presentation.*

interface MainActivityViewModel : Navigate {

    class Base(
        private val navigationCommunication: NavigationCommunication.Mutable,
        dispatchers: DispatchersList
    ) :
        BaseViewModel(dispatchers), Communication.Observe<Screen>, MainActivityViewModel {

        override fun observe(owner: LifecycleOwner, observer: Observer<Screen>) {
            navigationCommunication.observe(owner, observer)
        }

        override fun navigate(screen: Screen) {
            navigationCommunication.map(screen)
        }
    }
}