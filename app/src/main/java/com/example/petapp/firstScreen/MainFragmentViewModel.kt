package com.example.petapp.firstScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.petapp.core.BaseViewModel

class MainFragmentViewModel : BaseViewModel() {
    private val _currentText: MutableLiveData<String> =
        MutableLiveData<String>("default string from livedata")
    val currentText: LiveData<String> = _currentText

    fun changeText() {
        _currentText.value = "changed livedata text"
    }
}
