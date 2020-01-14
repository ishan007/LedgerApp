package com.example.deliveryledger.repository

import android.util.Log
import androidx.paging.PagedList
import com.example.deliveryledger.repository.model.Delivery
import com.example.deliveryledger.util.AppConstants.TAG
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class DeliveryDataBoundaryCallback @Inject constructor( )
    : PagedList.BoundaryCallback<Delivery>(){


    enum class BoundaryState{
        INITIAL_ITEM_LOADED, END_ITEM_LOADED
    }

    val pageLoadState: BehaviorSubject<BoundaryState> = BehaviorSubject.create()

    override fun onZeroItemsLoaded() {
        Log.e(TAG,"OnZero Item loaded")
        pageLoadState.onNext(BoundaryState.INITIAL_ITEM_LOADED)
    }

    override fun onItemAtEndLoaded(itemAtEnd: Delivery) {
        Log.e(TAG,"On next Item loaded")
        pageLoadState.onNext(BoundaryState.END_ITEM_LOADED)
    }


}