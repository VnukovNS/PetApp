package com.example.petapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainFragmentViewModel : ViewModel() {
    private val _currentText: MutableLiveData<String> =
        MutableLiveData<String>("default string from livedata")
    val currentText: LiveData<String> = _currentText
    
    fun changeText() {
        _currentText.value = "changed livedata text"
    }
}