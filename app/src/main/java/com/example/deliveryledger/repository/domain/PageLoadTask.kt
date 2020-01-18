package com.example.deliveryledger.repository.domain

import com.example.deliveryledger.repository.DeliveryDataBoundaryCallback
import com.example.deliveryledger.repository.Repository
import com.example.deliveryledger.repository.domain.base.PageLoadUseCase
import com.example.deliveryledger.util.NetworkConstants.RETRY_COUNT
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PageLoadTask @Inject constructor(
    private val repository: Repository
) : PageLoadUseCase{


    override fun loadData(state: DeliveryDataBoundaryCallback.BoundaryState) : Observable<Unit>{
        return Observable.just(state)
            .map {
                var offset = 0
                if(state == DeliveryDataBoundaryCallback.BoundaryState.END_ITEM_LOADED){
                    offset = repository.getDeliveryCount()
                }
                val listObservable = repository.getListFromAPI(offset)
                repository.insertListIntoDB(listObservable.blockingSingle())

            }.retry(RETRY_COUNT)
            .subscribeOn(Schedulers.io())
    }



}