package com.example.deliveryledger.repository.domain.base

import com.example.deliveryledger.repository.entities.Delivery
import io.reactivex.Observable

interface DeliveryDetailUseCase {

    fun updateDeliveryFavoriteState(id: String, isFavorite: Boolean): Observable<Delivery>

}