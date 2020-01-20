package com.example.deliveryledger.viewmodel.events

import androidx.lifecycle.MutableLiveData
import com.example.deliveryledger.util.Util
import com.example.deliveryledger.view.fragment.DeliveryListFragment
import io.reactivex.Observer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class DeliveryObserver<T>(
    private val onEvent: MutableLiveData<OnEvent<*>>,
    private val disposable: CompositeDisposable
) : Observer<T> {
    override fun onComplete() {

    }

    override fun onSubscribe(d: Disposable) {
        disposable.add(d)
    }

    override fun onNext(t: T) {
    }

    override fun onError(e: Throwable) {
        Util.logError(e.localizedMessage ?: "Something went wrong")
        onEvent.postValue(OnEvent(OnLoadPageError(DeliveryListFragment::class.java)))
    }

}