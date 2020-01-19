package com.example.deliveryledger.repository.domain.task

import com.example.deliveryledger.repository.Repository
import com.example.deliveryledger.repository.domain.usecase.UpdateDeliveryDetailUseCase
import com.example.deliveryledger.repository.entities.Delivery
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class UpdateDeliveryDetailTask @Inject constructor(private val repository: Repository):
    UpdateDeliveryDetailUseCase {

    override fun updateDeliveryFavoriteState(delivery: Delivery): Observable<Delivery> {
        return Observable.just(delivery)
            .map { repository.updateDeliveryFavoriteState(it) }
            .subscribeOn(Schedulers.io())
    }
}