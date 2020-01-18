package com.example.deliveryledger.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.example.deliveryledger.repository.domain.base.PageLoadUseCase
import com.example.deliveryledger.repository.entities.Delivery
import com.example.deliveryledger.util.AppConstants.TAG
import com.example.deliveryledger.viewmodel.DeliveryObserver
import com.example.deliveryledger.viewmodel.OnEvent
import io.reactivex.disposables.CompositeDisposable
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
        Log.e(TAG,"OnZero Item loaded")
        pageLoadUseCase.loadData(BoundaryState.INITIAL_ITEM_LOADED)
            .subscribe(DeliveryObserver<Unit>(onEventObserver, disposable))

    }

    override fun onItemAtEndLoaded(itemAtEnd: Delivery) {
        Log.e(TAG,"On next Item loaded")
        pageLoadUseCase.loadData(BoundaryState.END_ITEM_LOADED)
            .subscribe(DeliveryObserver<Unit>(onEventObserver, disposable))
    }


    fun clear(){
        disposable.clear()
    }


}