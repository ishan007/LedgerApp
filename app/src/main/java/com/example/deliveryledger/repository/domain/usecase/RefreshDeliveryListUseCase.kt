package com.example.deliveryledger.repository.domain.usecase

import io.reactivex.Observable

interface RefreshDeliveryListUseCase {
    fun refreshData(): Observable<Unit>
}