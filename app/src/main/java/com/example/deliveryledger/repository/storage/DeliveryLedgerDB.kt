package com.example.deliveryledger.repository.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.deliveryledger.repository.model.Delivery
import com.example.deliveryledger.util.DatabaseConstants

@Database(entities = [Delivery::class],
    version = DatabaseConstants.DB_VERSION)
abstract class DeliveryLedgerDB : RoomDatabase(){

    abstract fun getDeliveryDao(): DeliveryDao
}