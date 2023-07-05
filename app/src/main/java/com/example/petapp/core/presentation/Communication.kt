package com.example.petapp.core.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.petapp.core.domain.Mapper

interface Communication {

    interface Observe<T : Any> {
        fun observe(owner: LifecycleOwner, observer: Observer<T>) = Unit
    }

    interface Update<T : Any> : Mapper.Unit<T>

    interface Mutable<T : Any> : Observe<T>, Update<T>

    abstract class Abstract<T : Any>(
        protected val liveData: MutableLiveData<T> = MutableLiveData(),
    ) : Mutable<T> {

        override fun map(source: T) {
            liveData.value = source
        }

        override fun observe(owner: LifecycleOwner, observer: Observer<T>) {
            liveData.observe(owner, observer)
        }
    }

    abstract class Ui<T : Any>(
        liveData: MutableLiveData<T> = MutableLiveData()
    ) : Abstract<T>(liveData) {

        override fun map(source: T) {
            liveData.value = source
        }
    }

    abstract class Post<T : Any>(
        liveData: MutableLiveData<T> = MutableLiveData()
    ) : Abstract<T>(liveData) {
        override fun map(source: T) = liveData.postValue(source)
    }

    abstract class SingleUi<T : Any> : Ui<T>(SingleLiveEvent())
    abstract class SinglePost<T : Any> : Post<T>(SingleLiveEvent())
}
