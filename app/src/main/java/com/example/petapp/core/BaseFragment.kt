package com.example.petapp.core

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

abstract class BaseFragment<T: ViewModel> : Fragment() {
    protected lateinit var viewModel: T

    protected abstract val viewModelClass: Class<T>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[viewModelClass]
    }
}