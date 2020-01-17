package com.example.deliveryledger.repository.network

import com.example.deliveryledger.repository.entities.Delivery
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query


interface RequestApi {

    @GET("/v2/deliveries")
    fun getDeliveriesList(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int ) : Observable<List<Delivery>>

}