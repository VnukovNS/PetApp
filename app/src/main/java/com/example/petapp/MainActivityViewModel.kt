package com.example.petapp

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.petapp.core.BaseViewModel
import com.example.petapp.core.presentation.Communication
import com.example.petapp.core.presentation.Navigate
import com.example.petapp.core.presentation.NavigationCommunication
import com.example.petapp.core.presentation.Screen

interface MainActivityViewModel : Navigate {

    class Base(private val navigationCommunication: NavigationCommunication.Mutable) :
        BaseViewModel(), Communication.Observe<Screen>, MainActivityViewModel {

        override fun observe(owner: LifecycleOwner, observer: Observer<Screen>) {
            navigationCommunication.observe(owner, observer)
        }
        override fun navigate(screen: Screen) {
            navigationCommunication.map(screen)
        }

    }
}