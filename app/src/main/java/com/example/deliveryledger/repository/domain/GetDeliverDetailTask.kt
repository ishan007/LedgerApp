package com.example.deliveryledger.repository.domain

import com.example.deliveryledger.repository.Repository
import com.example.deliveryledger.repository.domain.base.DeliveryDetailUseCase
import com.example.deliveryledger.repository.entities.Delivery
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GetDeliverDetailTask @Inject constructor(private val repository: Repository):
    DeliveryDetailUseCase{

    override fun getDeliveryDetail(id: String): Observable<Delivery> {
        return Observable.just(repository.getDelivery(id)).subscribeOn(Schedulers.io())
    }

}