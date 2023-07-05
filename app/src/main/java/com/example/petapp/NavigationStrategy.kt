package com.example.petapp

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

interface NavigationStrategy {
    fun navigate(manager: FragmentManager, containerId: Int)

    abstract class Abstract(protected val fragment: Fragment) : NavigationStrategy {
        override fun navigate(manager: FragmentManager, containerId: Int) {
            manager.beginTransaction()
                .executeTransaction(containerId)
                .commit()
        }

        protected abstract fun FragmentTransaction.executeTransaction(containerId: Int): FragmentTransaction
    }

    class ReplaceWOBackStack(fragment: Fragment) : Abstract(fragment) {
        override fun FragmentTransaction.executeTransaction(containerId: Int): FragmentTransaction =
            replace(containerId, fragment)
    }

    class Replace(fragment: Fragment) : Abstract(fragment) {
        override fun FragmentTransaction.executeTransaction(containerId: Int): FragmentTransaction =
            replace(containerId, fragment)
                .addToBackStack(fragment.javaClass.simpleName)
    }

    class Add(fragment: Fragment) : Abstract(fragment) {
        override fun FragmentTransaction.executeTransaction(containerId: Int): FragmentTransaction =
            add(containerId, fragment)
                .addToBackStack(fragment.javaClass.simpleName)
    }

    object Pop : NavigationStrategy {
        override fun navigate(manager: FragmentManager, containerId: Int) {
            manager.popBackStack()
        }
    }
}
