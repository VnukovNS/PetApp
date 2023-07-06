package com.example.petapp.chooseCategoryScreen.presentation

import com.example.petapp.core.presentation.Communication

interface NewsCategoryCommunication : Communication.Mutable<NewsCategoryUi> {

    class Base : Communication.Abstract<NewsCategoryUi>(), NewsCategoryCommunication
}
