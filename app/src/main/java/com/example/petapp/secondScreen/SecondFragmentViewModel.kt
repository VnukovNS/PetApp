package com.example.petapp.secondScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.petapp.core.BaseViewModel
import com.example.petapp.core.cloud.ProvideConverterFactory
import com.example.petapp.core.cloud.ProvideLoggingInterceptor
import com.example.petapp.core.cloud.ProvideOkHttpClientBuilder
import com.example.petapp.core.cloud.ProvideRetrofitBuilder
import com.example.petapp.core.cloud.news.NewsMakeService
import com.example.petapp.core.cloud.news.NewsService


class SecondFragmentViewModel(
    private val service: NewsService = NewsMakeService(
        ProvideRetrofitBuilder.Base(
            ProvideConverterFactory.Base(),
            ProvideOkHttpClientBuilder.AddInterceptor(
                ProvideLoggingInterceptor.Debug(),
                ProvideOkHttpClientBuilder.Base()
            )
        )
    ).service(NewsService::class.java)
) : BaseViewModel() {
    private val _titleText: MutableLiveData<String> =
        MutableLiveData<String>("second fragment initial text")
    val titleText: LiveData<String> = _titleText
    private val _descriptionText: MutableLiveData<String> =
        MutableLiveData<String>("second fragment initial text")
    val descriptionText: LiveData<String> = _descriptionText


    fun getAutomobileNews() = handle({
        service.getNews().execute()
    }, {
        if (it.isSuccessful) {
            _titleText.value = it.body()!!.data[0].title
            _descriptionText.value = it.body()!!.data[0].content
        } else {
            _titleText.value = "error, smth go wrong"
            _descriptionText.value = "ERROR"
        }
    })

}