package com.example.deliveryledger.viewmodel

import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.deliveryledger.repository.DeliveryDataBoundaryCallback
import com.example.deliveryledger.repository.domain.DeliverDetailUseCase
import com.example.deliveryledger.repository.domain.DeliveryListUseCase
import com.example.deliveryledger.repository.model.Delivery
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject


class DeliveryLedgerViewModel @Inject constructor(
    private val deliveryListUseCase: DeliveryListUseCase,
    private val deliveryDetailUseCase: DeliverDetailUseCase,
    private val boundaryCallback: DeliveryDataBoundaryCallback,
    private val disposable: CompositeDisposable,
    private val onEventObserver: PublishSubject<OnEvent<*>>
): ViewModel(){


    val selectedDeliverySubject: BehaviorSubject<Delivery> = BehaviorSubject.create()

    val deliveryList: Observable<PagedList<Delivery>> by lazy {

        boundaryCallback.pageLoadState.observeOn(Schedulers.io())
            .subscribe(object: DeliveryObserver<DeliveryDataBoundaryCallback.BoundaryState>(onEventObserver, disposable){

                override fun onNext(t: DeliveryDataBoundaryCallback.BoundaryState) {
                    deliveryListUseCase.loadData(t).subscribe(DeliveryObserver(onEventObserver, disposable))
                }

            })

        val rxPagedListBuilder = deliveryListUseCase.getDeliveries()
        rxPagedListBuilder.setBoundaryCallback(boundaryCallback)
        rxPagedListBuilder.buildObservable()
    }

    fun updateDeliveryFavoriteState(delivery: Delivery){
        deliveryDetailUseCase.updateDeliveryFavoriteState(delivery.id, !delivery.isFavorite)
            .subscribe(object : DeliveryObserver<Delivery>(onEventObserver, disposable){
                override fun onNext(t: Delivery) {
                    onDeliverySelected(t)
                }
            })
    }

    fun onDeliverySelected(delivery: Delivery){
        selectedDeliverySubject.onNext(delivery)
    }

    fun getEventObserver() : PublishSubject<OnEvent<*>>{
        return onEventObserver
    }

    fun refreshData(): Observable<Unit>{
        return deliveryListUseCase.refreshData()
    }

    override fun onCleared() {
        disposable.clear()
        super.onCleared()
    }

}