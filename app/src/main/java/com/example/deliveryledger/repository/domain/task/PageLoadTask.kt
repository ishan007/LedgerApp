package com.example.deliveryledger.repository.domain.task

import com.example.deliveryledger.repository.DeliveryDataBoundaryCallback
import com.example.deliveryledger.repository.Repository
import com.example.deliveryledger.repository.domain.usecase.PageLoadUseCase
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PageLoadTask @Inject constructor(
    private val repository: Repository
) : PageLoadUseCase{


    override fun loadData(state: DeliveryDataBoundaryCallback.BoundaryState) : Observable<Unit>{
        return Observable.just(state).flatMap {
            var offset = 0
            if(state == DeliveryDataBoundaryCallback.BoundaryState.END_ITEM_LOADED){
                offset = repository.getDeliveryCount()
            }
            repository.getListFromAPI(offset)
        }.flatMap {
            Observable.just(repository.insertListIntoDB(it))
        }.subscribeOn(Schedulers.io())
    }



}