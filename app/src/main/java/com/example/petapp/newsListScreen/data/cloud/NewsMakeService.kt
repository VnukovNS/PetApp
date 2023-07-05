package com.example.petapp.newsListScreen.data.cloud

import com.example.petapp.core.data.cloud.MakeService
import com.example.petapp.core.data.cloud.ProvideRetrofitBuilder

class NewsMakeService(retrofitBuilder: ProvideRetrofitBuilder) :
    MakeService.Abstract(retrofitBuilder, "https://inshorts-news-knbl9pokb-sumanjay.vercel.app/")
