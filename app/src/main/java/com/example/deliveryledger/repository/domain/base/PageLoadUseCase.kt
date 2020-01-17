package com.example.deliveryledger.repository.domain.base

import com.example.deliveryledger.repository.DeliveryDataBoundaryCallback
import io.reactivex.Observable

interface PageLoadUseCase {

    fun loadData(state: DeliveryDataBoundaryCallback.BoundaryState): Observable<Unit>

}