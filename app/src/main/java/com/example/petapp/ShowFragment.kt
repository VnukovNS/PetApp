package com.example.petapp

import androidx.fragment.app.Fragment

interface ShowFragment{
    fun show(fragment: Fragment)

    class Empty(): ShowFragment {
        override fun show(fragment: Fragment) {
            return Unit
        }
    }
}