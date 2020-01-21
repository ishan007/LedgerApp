package com.example.deliveryledger.repository.domain.task

import com.example.deliveryledger.repository.DeliveryDataBoundaryCallback
import com.example.deliveryledger.repository.Repository
import com.example.deliveryledger.repository.domain.usecase.PageLoadUseCase
import com.example.deliveryledger.util.Util
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PageLoadTask @Inject constructor(
    private val repository: Repository
) : PageLoadUseCase{


    // fetch data from API and inserts it into db. It returns the list size which is used to
    // determine whether page loading is completed
    override fun loadData(state: DeliveryDataBoundaryCallback.BoundaryState) : Observable<Int>{
        return Observable.just(state).flatMap {
            var offset = 0
            if(state == DeliveryDataBoundaryCallback.BoundaryState.END_ITEM_LOADED){
                offset = repository.getDeliveryCount()
            }
            repository.getListFromAPI(offset)
        }.flatMap {
            Util.logDebug("Response size ${it.size}")
            repository.insertListIntoDB(it)
            Observable.just(it.size)
        }.subscribeOn(Schedulers.io())
    }



}