package com.example.deliveryledger.viewmodel

import androidx.paging.PagedList
import com.example.deliveryledger.repository.DeliveryDataBoundaryCallback
import com.example.deliveryledger.repository.domain.base.DeliveryListUseCase
import com.example.deliveryledger.repository.domain.base.RefreshDeliveryListUseCase
import com.example.deliveryledger.repository.entities.Delivery
import io.reactivex.Observable
import javax.inject.Inject

class DeliveryListViewModel @Inject constructor(
    private val deliveryListUseCase: DeliveryListUseCase,
    private val refreshDeliveryListUseCase: RefreshDeliveryListUseCase,
    private val boundaryCallback: DeliveryDataBoundaryCallback
) : BaseViewModel(){


    val deliveryList: Observable<PagedList<Delivery>> by lazy {
        val rxPagedListBuilder = deliveryListUseCase.getDeliveries()
        rxPagedListBuilder.setBoundaryCallback(boundaryCallback)
        rxPagedListBuilder.buildObservable()
    }

    fun refreshData(): Observable<Unit> {
        return refreshDeliveryListUseCase.refreshData()
    }

    override fun onCleared() {
        boundaryCallback.clear()
        super.onCleared()
    }

}