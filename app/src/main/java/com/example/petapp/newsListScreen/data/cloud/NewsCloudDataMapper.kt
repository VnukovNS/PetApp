package com.example.petapp.newsListScreen.data.cloud

class NewsCloudDataMapper : NewsCloud.Mapper<List<NewsDataCloud.Base>> {
    override fun map(category: String, data: MutableList<NewsDataCloud.Base>): List<NewsDataCloud.Base> = data
}