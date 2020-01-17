package com.example.deliveryledger.repository.domain

import androidx.paging.RxPagedListBuilder
import com.example.deliveryledger.repository.Repository
import com.example.deliveryledger.repository.domain.base.DeliveryListUseCase
import com.example.deliveryledger.repository.entities.Delivery
import com.example.deliveryledger.util.NetworkConstants
import javax.inject.Inject

class GetDeliveryListTask @Inject constructor(private val repository: Repository)
    : DeliveryListUseCase {


    override fun getDeliveries() : RxPagedListBuilder<Int, Delivery> {
        return RxPagedListBuilder<Int, Delivery>(repository.getDeliveryListDataSource(),
            NetworkConstants.RESPONSE_LIMIT)
    }


}