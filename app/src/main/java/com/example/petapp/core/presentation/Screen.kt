package com.example.petapp.core.presentation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import java.io.Serializable

interface Screen : Serializable {

    fun navigate(manager: FragmentManager, containerId: Int)

    abstract class Add(private val clazz: Class<out Fragment>) : Screen {
        override fun navigate(manager: FragmentManager, containerId: Int) {
            manager.beginTransaction()
                .add(containerId, clazz.newInstance())
                .addToBackStack(clazz.canonicalName)
                .commit()
        }
    }

    abstract class Replace(private val clazz: Class<out Fragment>) : Screen {
        override fun navigate(manager: FragmentManager, containerId: Int) {
            manager.beginTransaction()
                .replace(containerId, clazz.newInstance())
                .commit()
        }
    }

    abstract class ReplaceWithBackStack(private val clazz: Class<out Fragment>) : Screen {
        override fun navigate(manager: FragmentManager, containerId: Int) {
            manager.beginTransaction()
                .replace(containerId, clazz.newInstance())
                .addToBackStack(clazz.canonicalName)
                .commit()
        }
    }

    object Pop : Screen {
        override fun navigate(manager: FragmentManager, containerId: Int) {
            manager.popBackStack()
        }
    }
}