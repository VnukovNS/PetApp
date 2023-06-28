package com.example.petapp.secondScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.petapp.core.BaseViewModel
import com.example.petapp.data.NewsResult
import com.example.petapp.domain.NewsInteractor


class SecondFragmentViewModel(private val interactor : NewsInteractor = NewsInteractor.Base()) : BaseViewModel() {
    private val _titleText: MutableLiveData<String> =
        MutableLiveData<String>("second fragment initial text")
    val titleText: LiveData<String> = _titleText
    private val _descriptionText: MutableLiveData<String> =
        MutableLiveData<String>("second fragment initial text")
    val descriptionText: LiveData<String> = _descriptionText

    fun getNews() = handle({ interactor.getNews("automobile") }, {
        if (it is NewsResult.Success) {
            _titleText.value = it.news[0].title
            _descriptionText.value = it.news[0].content
        } else if ( it is NewsResult.Error) {
            _titleText.value = it.message
            _descriptionText.value = ""
        }
    })
}
