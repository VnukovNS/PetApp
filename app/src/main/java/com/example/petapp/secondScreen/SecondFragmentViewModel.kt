package com.example.petapp.secondScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.petapp.core.BaseViewModel


class SecondFragmentViewModel : BaseViewModel() {
    private val _currentText: MutableLiveData<String> =
        MutableLiveData<String>("second fragment initial text")
    val currentText: LiveData<String> = _currentText

    fun changeText() {
        _currentText.value = "changed livedata text for 2nd screen"
    }

}