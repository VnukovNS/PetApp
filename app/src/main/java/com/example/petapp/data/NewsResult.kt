package com.example.petapp.data

interface NewsResult {

    // todo если потом сделать маппер, то можно обойтись без открытого геттера
    class Success(val news: List<NewsData>) : NewsResult

    class Error(val message: String) : NewsResult
}
