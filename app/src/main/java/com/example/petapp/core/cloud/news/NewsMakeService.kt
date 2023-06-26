package com.example.petapp.core.cloud.news

import com.example.petapp.core.cloud.MakeService
import com.example.petapp.core.cloud.ProvideRetrofitBuilder

class NewsMakeService(retrofitBuilder: ProvideRetrofitBuilder) :
    MakeService.Abstract(retrofitBuilder, "https://inshorts-news-knbl9pokb-sumanjay.vercel.app/")