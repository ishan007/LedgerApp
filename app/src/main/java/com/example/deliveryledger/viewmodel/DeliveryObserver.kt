package com.example.deliveryledger.viewmodel

import io.reactivex.Observer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject

open class DeliveryObserver<T>(private val onEvent: PublishSubject<OnEvent<*>>,
                               private val disposable: CompositeDisposable) : Observer<T>{
    override fun onComplete() {

    }

    override fun onSubscribe(d: Disposable) {
        disposable.add(d)
    }

    override fun onNext(t: T) {
    }

    override fun onError(e: Throwable) {
        onEvent.onNext(OnEvent(OnNetworkError(e.localizedMessage ?: "Something went wrong")))
    }

}