package com.example.petapp.core.presentation

interface NavigationCommunication {

    interface Update : Communication.Update<Screen>

    interface Observe : Communication.Observe<Screen>

    interface Mutable : Update, Observe

    class Base : Communication.SingleUi<Screen>(), Mutable
}