package com.example.petapp.newsListScreen.presentation

import com.example.petapp.core.presentation.Communication
import com.example.petapp.newsListScreen.NewsUi

interface NewsListCommunication: Communication.Mutable<NewsUi> {

    class Base : Communication.Abstract<NewsUi>(), NewsListCommunication
}