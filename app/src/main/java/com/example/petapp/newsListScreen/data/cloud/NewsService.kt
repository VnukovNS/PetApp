package com.example.petapp.newsListScreen.data.cloud


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("news")
    fun getNews(
        @Query("category") category: String = "automobile"
    ): Call<NewsCloud.Base>
}
