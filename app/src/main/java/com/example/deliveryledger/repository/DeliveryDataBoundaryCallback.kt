package com.example.deliveryledger.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.example.deliveryledger.repository.domain.usecase.PageLoadUseCase
import com.example.deliveryledger.repository.entities.Delivery
import com.example.deliveryledger.util.Util
import com.example.deliveryledger.view.fragment.DeliveryListFragment
import com.example.deliveryledger.viewmodel.events.DeliveryObserver
import com.example.deliveryledger.viewmodel.events.OnEvent
import com.example.deliveryledger.viewmodel.events.OnHideLoader
import com.example.deliveryledger.viewmodel.events.OnShowLoader
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class DeliveryDataBoundaryCallback @Inject constructor(
    private val pageLoadUseCase: PageLoadUseCase,
    private val disposable: CompositeDisposable,
    private val onEventObserver: MutableLiveData<OnEvent<*>>
) : PagedList.BoundaryCallback<Delivery>(){


    enum class BoundaryState{
        INITIAL_ITEM_LOADED, END_ITEM_LOADED
    }

    override fun onZeroItemsLoaded() {
        Util.logDebug("OnZero Item loaded")
        pageLoadUseCase.loadData(BoundaryState.INITIAL_ITEM_LOADED)
            .subscribe(observer)
    }

    override fun onItemAtEndLoaded(itemAtEnd: Delivery) {
        Util.logDebug("On next Item loaded")
        pageLoadUseCase.loadData(BoundaryState.END_ITEM_LOADED)
            .subscribe(observer)
    }

    fun retry(){
        pageLoadUseCase.loadData(BoundaryState.END_ITEM_LOADED)
            .subscribe(observer)
    }

    private val observer = object : DeliveryObserver<Unit>(onEventObserver, disposable){
        override fun onSubscribe(d: Disposable) {
            super.onSubscribe(d)
            onEventObserver.postValue(OnEvent(OnShowLoader(DeliveryListFragment::class.java)))
        }

        override fun onComplete() {
            super.onComplete()
            onEventObserver.postValue(OnEvent(OnHideLoader(DeliveryListFragment::class.java)))
        }

        override fun onError(e: Throwable) {
            super.onError(e)
            onEventObserver.postValue(OnEvent(OnHideLoader(DeliveryListFragment::class.java)))
        }
    }


    fun clear(){
        disposable.clear()
    }


}