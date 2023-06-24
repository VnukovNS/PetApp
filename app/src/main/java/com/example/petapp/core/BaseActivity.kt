package com.example.petapp.core

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

abstract class BaseActivity<T: ViewModel> : AppCompatActivity() {
    protected lateinit var viewModel: T

    protected abstract val viewModelClass: Class<T>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // todo перенести создание вм в апликейшн?
        viewModel = ViewModelProvider(this)[viewModelClass]
    }
}