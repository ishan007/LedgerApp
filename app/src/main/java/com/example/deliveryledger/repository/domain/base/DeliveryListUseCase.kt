package com.example.deliveryledger.repository.domain.base

import androidx.paging.RxPagedListBuilder
import com.example.deliveryledger.repository.entities.Delivery

interface DeliveryListUseCase {

    fun getDeliveries() : RxPagedListBuilder<Int, Delivery>

}