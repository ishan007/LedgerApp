package com.example.deliveryledger.repository.domain

import com.example.deliveryledger.repository.DeliveryDataBoundaryCallback
import com.example.deliveryledger.repository.Repository
import com.example.deliveryledger.repository.domain.base.PageLoadUseCase
import io.reactivex.Observable
import javax.inject.Inject

class PageLoadTask @Inject constructor(private val repository: Repository) : PageLoadUseCase{

    private fun loadInitialData() : Observable<Unit>{

        return repository.getListFromAPI(0).map {
            repository.insertListIntoDB(it)
        }

    }

    private fun loadNextData() : Observable<Unit>{
        return repository.getListFromAPI(repository.getDeliveryCount()).map {
            repository.insertListIntoDB(it)
        }
    }

    override fun loadData(state: DeliveryDataBoundaryCallback.BoundaryState): Observable<Unit> = when(state) {
        DeliveryDataBoundaryCallback.BoundaryState.INITIAL_ITEM_LOADED -> loadInitialData()
        DeliveryDataBoundaryCallback.BoundaryState.END_ITEM_LOADED -> loadNextData()
    }
}