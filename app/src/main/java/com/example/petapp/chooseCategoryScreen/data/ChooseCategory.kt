package com.example.petapp.chooseCategoryScreen.data

interface ChooseCategory {

    interface Mutable {

        fun save(data: String)

        fun read(): String
    }

    class Base : Mutable {

        private var value = ""

        override fun save(data: String) {
            value = data
        }

        override fun read(): String = value
    }
}
