package com.example.petapp.data

import android.util.Log
import com.example.petapp.data.news.NewsService
import com.example.petapp.domain.NoConnectionException
import com.example.petapp.domain.ServiceUnavailableException
import java.net.UnknownHostException

class NewsCloudDataSource(private val service: NewsService) {

    fun getNewsServerModel(category: String) = service.getNews(category)

    fun getNews(category: String): MutableList<NewsData> {
        try {
            return getNewsServerModel(category).execute().body()!!.data
        } catch (e: Exception) {
            Log.e("vniks", "exception ${e.message}")
            if (e is UnknownHostException) {
                throw NoConnectionException()
            } else {
                throw ServiceUnavailableException()
            }
        }
    }
}
