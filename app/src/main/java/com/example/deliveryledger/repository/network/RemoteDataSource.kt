package com.example.deliveryledger.repository.network

import com.example.deliveryledger.repository.entities.Delivery
import com.example.deliveryledger.util.NetworkConstants
import io.reactivex.Observable
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val requestApi: RequestApi){

    fun getDeliveriesListFromAPI(offset: Int) : Observable<List<Delivery>>{
        return requestApi.getDeliveriesList(offset, NetworkConstants.PAGE_LIMIT)
    }

}