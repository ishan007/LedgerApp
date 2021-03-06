package com.example.deliveryledger.viewmodel

import androidx.paging.PagedList
import com.example.deliveryledger.di.view.ActivityScope
import com.example.deliveryledger.repository.DeliveryDataBoundaryCallback
import com.example.deliveryledger.repository.domain.usecase.DeliveryListUseCase
import com.example.deliveryledger.repository.domain.usecase.RefreshDeliveryListUseCase
import com.example.deliveryledger.repository.entities.Delivery
import io.reactivex.Observable
import javax.inject.Inject

/**
 * ViewModel class responsible for handling data for DeliveryListFragment
 */
@ActivityScope
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

    fun retry(){
        boundaryCallback.retry()
    }

    override fun onCleared() {
        boundaryCallback.clear()
        super.onCleared()
    }

}