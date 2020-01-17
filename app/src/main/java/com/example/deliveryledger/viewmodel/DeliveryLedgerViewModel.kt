package com.example.deliveryledger.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.deliveryledger.repository.DeliveryDataBoundaryCallback
import com.example.deliveryledger.repository.domain.base.DeliveryDetailUseCase
import com.example.deliveryledger.repository.domain.base.DeliveryListUseCase
import com.example.deliveryledger.repository.domain.base.PageLoadUseCase
import com.example.deliveryledger.repository.domain.base.RefreshDeliveryListUseCase
import com.example.deliveryledger.repository.entities.Delivery
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class DeliveryLedgerViewModel @Inject constructor(
    private val deliveryListUseCase: DeliveryListUseCase,
    private val deliverDetailUseCase: DeliveryDetailUseCase,
    private val pageLoadUseCase: PageLoadUseCase,
    private val refreshDeliveryListUseCase: RefreshDeliveryListUseCase,
    private val boundaryCallback: DeliveryDataBoundaryCallback,
    private val disposable: CompositeDisposable,
    private val onEventObserver: MutableLiveData<OnEvent<*>>
): ViewModel(){


    private val _selectedDelivery = MutableLiveData<Delivery>()
    val selectedDelivery : LiveData<Delivery> = _selectedDelivery

    val deliveryList: Observable<PagedList<Delivery>> by lazy {

        boundaryCallback.pageLoadState.observeOn(Schedulers.io())
            .subscribe(object: DeliveryObserver<DeliveryDataBoundaryCallback.BoundaryState>(onEventObserver, disposable){

                override fun onNext(t: DeliveryDataBoundaryCallback.BoundaryState) {
                    pageLoadUseCase.loadData(t).subscribe(DeliveryObserver(onEventObserver, disposable))
                }

            })

        val rxPagedListBuilder = deliveryListUseCase.getDeliveries()
        rxPagedListBuilder.setBoundaryCallback(boundaryCallback)
        rxPagedListBuilder.buildObservable()
    }

    fun updateDeliveryFavoriteState(delivery: Delivery){
        deliverDetailUseCase.updateDeliveryFavoriteState(delivery.id, !delivery.isFavorite)
            .subscribe(object : DeliveryObserver<Delivery>(onEventObserver, disposable){
                override fun onNext(t: Delivery) {
                    onDeliverySelected(t)
                }
            })
    }

    fun onDeliverySelected(delivery: Delivery){
        _selectedDelivery.postValue(delivery)
    }

    fun refreshData(): Observable<Unit>{
        return refreshDeliveryListUseCase.refreshData()
    }

    override fun onCleared() {
        disposable.clear()
        super.onCleared()
    }

}