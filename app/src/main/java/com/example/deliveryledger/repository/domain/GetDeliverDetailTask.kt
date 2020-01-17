package com.example.deliveryledger.repository.domain

import com.example.deliveryledger.repository.Repository
import com.example.deliveryledger.repository.domain.base.DeliveryDetailUseCase
import com.example.deliveryledger.repository.entities.Delivery
import io.reactivex.Observable
import javax.inject.Inject

class GetDeliverDetailTask @Inject constructor(private val repository: Repository):
    DeliveryDetailUseCase{

    override fun updateDeliveryFavoriteState(id: String, isFavorite: Boolean): Observable<Delivery>{
        return repository.updateDeliveryFavoriteState(id, isFavorite)
    }

}