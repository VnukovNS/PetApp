package com.example.petapp.newsListScreen.data.cloud

import android.util.Log
import com.example.petapp.core.data.cloud.errors.NoConnectionException
import com.example.petapp.core.data.cloud.errors.ServiceUnavailableException
import java.net.UnknownHostException

class NewsCloudDataSource(private val service: NewsService, private val mapper: NewsCloudDataMapper) {

    fun getNewsServerModel(category: String) = service.getNews(category)

    fun getNews(category: String): List<NewsData.Base> {
        try {
            val newsCloud = getNewsServerModel(category).execute().body()!!
            return newsCloud.map(mapper)
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
