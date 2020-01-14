package com.example.deliveryledger.repository.storage

import androidx.paging.DataSource
import com.example.deliveryledger.repository.model.Delivery
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val database: DeliveryLedgerDB){

    fun getDeliveryListDataSource() : DataSource.Factory<Int, Delivery>{
        return database.getDeliveryDao().getDeliveryList()
    }

    fun updateDeliveryFavoriteState(id: String, isFavorite: Boolean): Observable<Delivery> {
        return Observable.just(database.getDeliveryDao()).map {
            it.updateDeliveryFavoriteState(id, isFavorite)
            it.getDelivery(id)
        }.subscribeOn(Schedulers.io())

    }

    fun insertListIntoDB(list: List<Delivery>){
        database.getDeliveryDao().insertDeliveryEntry(list)
    }

    fun getDeliveryCount(): Int{
        return database.getDeliveryDao().getDeliveryCount()
    }

    fun clearDb(){
        database.clearAllTables()
    }
}