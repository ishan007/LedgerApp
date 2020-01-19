package com.example.deliveryledger.viewmodel.events

import androidx.lifecycle.MutableLiveData
import io.reactivex.Observer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class DeliveryObserver<T>(private val onEvent: MutableLiveData<OnEvent<*>>,
                               private val disposable: CompositeDisposable) : Observer<T>{
    override fun onComplete() {

    }

    override fun onSubscribe(d: Disposable) {
        disposable.add(d)
    }

    override fun onNext(t: T) {
    }

    override fun onError(e: Throwable) {
        onEvent.postValue(
            OnEvent(
                OnNetworkError(
                    e.localizedMessage ?: "Something went wrong"
                )
            )
        )
    }

}