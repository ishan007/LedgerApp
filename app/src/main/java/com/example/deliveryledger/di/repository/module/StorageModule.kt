package com.example.deliveryledger.di.repository.module

import android.app.Application
import androidx.room.Room
import com.example.deliveryledger.repository.storage.DeliveryLedgerDB
import com.example.deliveryledger.util.DatabaseConstants
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class StorageModule {

    @Provides
    @Singleton
    fun provideDeliveryLedgerDB(application: Application): DeliveryLedgerDB {
        return Room.databaseBuilder(
            application,
            DeliveryLedgerDB::class.java, DatabaseConstants.DB_NAME
        ).build()
    }

}