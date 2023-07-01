package com.example.petapp.data.news

import com.example.petapp.data.NewsModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("news")
    fun getNews(
        @Query("category") category: String = "automobile"
    ): Call<NewsModel>
}
