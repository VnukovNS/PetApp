package com.example.petapp.newsListScreen.presentation

import com.example.petapp.core.presentation.Communication

interface ErrorCommunication : Communication.Mutable<String> {

    class Base : Communication.SingleUi<String>(), ErrorCommunication
}
