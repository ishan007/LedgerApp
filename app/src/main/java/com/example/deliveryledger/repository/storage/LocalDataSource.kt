package com.example.deliveryledger.repository.storage

import androidx.paging.DataSource
import com.example.deliveryledger.repository.entities.Delivery
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val database: DeliveryLedgerDB){

    fun getDeliveryListDataSource() : DataSource.Factory<Int, Delivery>{
        return database.getDeliveryDao().getDeliveryList()
    }

    fun updateDeliveryFavoriteState(delivery: Delivery): Delivery {
        database.getDeliveryDao().updateDeliveryFavoriteState(delivery)
        return getDelivery(delivery.id)
    }

    fun insertListIntoDB(list: List<Delivery>){
        database.getDeliveryDao().insertDeliveryEntry(list)
    }

    fun getDeliveryCount(): Int{
        return database.getDeliveryDao().getDeliveryCount()
    }

    fun getDelivery(deliveryId: String): Delivery{
        return database.getDeliveryDao().getDelivery(deliveryId)
    }

    fun clearDb(){
        database.clearAllTables()
    }
}