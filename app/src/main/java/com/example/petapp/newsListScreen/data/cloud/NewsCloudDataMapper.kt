package com.example.petapp.newsListScreen.data.cloud

class NewsCloudDataMapper : NewsCloud.Mapper<List<NewsData.Base>> {
    override fun map(category: String, data: MutableList<NewsData.Base>): List<NewsData.Base> = data
}