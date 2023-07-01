package com.example.petapp.secondScreen

import com.example.petapp.core.presentation.Communication

interface NewsCommunication: Communication.Mutable<NewsUi> {

    class Base : Communication.Abstract<NewsUi>(), NewsCommunication
}