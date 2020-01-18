package com.example.deliveryledger.repository

import androidx.paging.DataSource
import com.example.deliveryledger.repository.entities.Delivery
import com.example.deliveryledger.repository.network.RemoteDataSource
import com.example.deliveryledger.repository.storage.LocalDataSource
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor( private val localDataSource: LocalDataSource,
                                      private val remoteDataSource: RemoteDataSource){


    fun getDeliveryListDataSource() : DataSource.Factory<Int, Delivery>{
        return localDataSource.getDeliveryListDataSource()
    }

    fun updateDeliveryFavoriteState(delivery: Delivery): Delivery{
        return localDataSource.updateDeliveryFavoriteState(delivery)
    }

    fun getListFromAPI(offset: Int) : Observable<List<Delivery>>{
        return remoteDataSource.getDeliveriesListFromAPI(offset)
    }

    fun insertListIntoDB(list: List<Delivery>){
        localDataSource.insertListIntoDB(list)
    }

    fun getDeliveryCount(): Int{
        return localDataSource.getDeliveryCount()
    }

    fun getDelivery(deliveryId: String) : Delivery{
        return localDataSource.getDelivery(deliveryId)
    }

    fun clearDb(){
        localDataSource.clearDb()
    }

}