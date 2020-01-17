package com.example.deliveryledger.repository.domain.base

import io.reactivex.Observable

interface RefreshDeliveryListUseCase {
    fun refreshData(): Observable<Unit>
}