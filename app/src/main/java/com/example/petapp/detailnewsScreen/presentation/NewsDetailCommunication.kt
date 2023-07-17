package com.example.petapp.detailnewsScreen.presentation

import com.example.petapp.core.presentation.Communication

interface NewsDetailCommunication: Communication.Mutable<DetailsNewsUi> {

    class Base: Communication.Abstract<DetailsNewsUi>(), NewsDetailCommunication
}