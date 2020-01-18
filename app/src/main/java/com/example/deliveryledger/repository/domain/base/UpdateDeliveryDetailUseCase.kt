package com.example.deliveryledger.repository.domain.base

import com.example.deliveryledger.repository.entities.Delivery
import io.reactivex.Observable

interface UpdateDeliveryDetailUseCase {

    fun updateDeliveryFavoriteState(delivery: Delivery): Observable<Delivery>

}