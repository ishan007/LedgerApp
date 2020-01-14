package com.example.deliveryledger.repository.domain

import androidx.paging.RxPagedListBuilder
import com.example.deliveryledger.repository.DeliveryDataBoundaryCallback
import com.example.deliveryledger.repository.Repository
import com.example.deliveryledger.repository.model.Delivery
import com.example.deliveryledger.util.NetworkConstants
import io.reactivex.Observable
import javax.inject.Inject

class DeliveryListUseCase @Inject constructor(private val repository: Repository)
    : DeliveryLedgerUseCase {


    fun getDeliveries() : RxPagedListBuilder<Int, Delivery> {
        return RxPagedListBuilder<Int, Delivery>(repository.getDeliveryListDataSource(),
            NetworkConstants.RESPONSE_LIMIT)
    }


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

    fun loadData(state: DeliveryDataBoundaryCallback.BoundaryState): Observable<Unit> = when(state) {
        DeliveryDataBoundaryCallback.BoundaryState.INITIAL_ITEM_LOADED -> loadInitialData()
        DeliveryDataBoundaryCallback.BoundaryState.END_ITEM_LOADED -> loadNextData()
    }

    fun refreshData(): Observable<Unit>{
        return repository.getListFromAPI(0).map {
            repository.clearDb()
            repository.insertListIntoDB(it)
        }
    }



}