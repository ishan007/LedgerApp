package com.example.deliveryledger.viewmodel.events

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.deliveryledger.util.AppConstants.TAG
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

        Log.e(TAG, "Thread name = ${Thread.currentThread().name}")

        onEvent.postValue(
            OnEvent(
                OnNetworkError(
                    e.localizedMessage ?: "Something went wrong"
                )
            )
        )
    }

}