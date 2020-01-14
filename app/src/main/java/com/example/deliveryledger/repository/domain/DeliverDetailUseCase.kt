package com.example.deliveryledger.repository.domain

import com.example.deliveryledger.repository.Repository
import com.example.deliveryledger.repository.model.Delivery
import io.reactivex.Observable
import javax.inject.Inject

class DeliverDetailUseCase @Inject constructor(private val repository: Repository):
    DeliveryLedgerUseCase{

    fun updateDeliveryFavoriteState(id: String, isFavorite: Boolean): Observable<Delivery>{
        return repository.updateDeliveryFavoriteState(id, isFavorite)
    }

}