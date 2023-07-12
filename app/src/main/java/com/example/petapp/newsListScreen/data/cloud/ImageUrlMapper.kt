package com.example.petapp.newsListScreen.data.cloud

class ImageUrlMapper : NewsDataCloud.Mapper<String> {
    override fun map(
        author: String,
        content: String,
        date: String,
        imageUrl: String,
        readMoreUrl: String,
        title: String
    ): String = imageUrl
}
