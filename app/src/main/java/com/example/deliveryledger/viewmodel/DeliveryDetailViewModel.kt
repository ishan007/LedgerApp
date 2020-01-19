package com.example.deliveryledger.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.deliveryledger.repository.domain.usecase.DeliveryDetailUseCase
import com.example.deliveryledger.repository.domain.usecase.UpdateDeliveryDetailUseCase
import com.example.deliveryledger.repository.entities.Delivery
import com.example.deliveryledger.viewmodel.events.DeliveryObserver
import com.example.deliveryledger.viewmodel.events.OnEvent
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class DeliveryDetailViewModel @Inject constructor(
    private val deliverDetailUseCase: DeliveryDetailUseCase,
    private val updateDeliveryDetailUseCase: UpdateDeliveryDetailUseCase,
    private val disposable: CompositeDisposable,
    private val onEventObserver: MutableLiveData<OnEvent<*>>
) : BaseViewModel() {


    private val _delivery = MutableLiveData<Delivery>()
    val delivery: LiveData<Delivery> = _delivery


    fun updateDeliveryFavoriteState(updateDeliveryDetail: Delivery){
        updateDeliveryDetailUseCase.updateDeliveryFavoriteState(updateDeliveryDetail)
            .subscribe(object : DeliveryObserver<Delivery>(onEventObserver, disposable){
                override fun onNext(t: Delivery) {
                    _delivery.postValue(t)
                }
            })
    }

    fun setSelectedDelivery(deliveryId: String){
        deliverDetailUseCase.getDeliveryDetail(deliveryId)
            .subscribe(object : DeliveryObserver<Delivery>(onEventObserver, disposable){
                override fun onNext(t: Delivery) {
                    _delivery.postValue(t)
                }
            })
    }

    override fun onCleared() {
        disposable.clear()
        super.onCleared()
    }

}