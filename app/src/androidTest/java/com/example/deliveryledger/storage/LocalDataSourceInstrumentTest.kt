package com.example.deliveryledger.storage

import android.content.Context
import androidx.room.Room
import androidx.room.paging.LimitOffsetDataSource
import androidx.test.platform.app.InstrumentationRegistry
import com.example.deliveryledger.BaseInstrumentedTest
import com.example.deliveryledger.LocalDataGeneratorTest
import com.example.deliveryledger.repository.entities.Delivery
import com.example.deliveryledger.repository.storage.DeliveryLedgerDB
import com.example.deliveryledger.repository.storage.LocalDataSource
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.io.IOException

class LocalDataSourceInstrumentTest : BaseInstrumentedTest() {

    private lateinit var db: DeliveryLedgerDB
    private lateinit var appContext: Context
    private lateinit var localDataSource: LocalDataSource


    @Before
    fun setup() {
        appContext = InstrumentationRegistry.getInstrumentation().targetContext
        db = Room.inMemoryDatabaseBuilder(appContext, DeliveryLedgerDB::class.java).build()
        localDataSource = LocalDataSource(db)
    }

    @Test
    fun testInsertData() {
        val list =
            LocalDataGeneratorTest.getDeliveryList()
        localDataSource.insertListIntoDB(list)
        val itemCount =
            (localDataSource.getDeliveryListDataSource().create() as LimitOffsetDataSource).countItems()
        Assert.assertEquals(list.size, itemCount)
        Assert.assertEquals(list.size, localDataSource.getDeliveryCount())
    }


    @Test
    fun testInsertSameData() {
        val list =
            LocalDataGeneratorTest.getDeliveryList()
        localDataSource.insertListIntoDB(list)
        localDataSource.insertListIntoDB(list)
        val updatedItemCount =
            (localDataSource.getDeliveryListDataSource().create() as LimitOffsetDataSource).countItems()
        Assert.assertTrue(updatedItemCount == list.size)
    }

    @Test
    fun testInsertEmptyList() {
        val list = LocalDataGeneratorTest.getDeliveryList()
        localDataSource.insertListIntoDB(list)

        val emptyList = arrayListOf<Delivery>()
        localDataSource.insertListIntoDB(emptyList)

        val updatedItemCount =
            (localDataSource.getDeliveryListDataSource().create() as LimitOffsetDataSource).countItems()
        Assert.assertTrue(updatedItemCount == list.size)
    }


    @Test
    fun testUpdateData() {
        val list = LocalDataGeneratorTest.getDeliveryList()
        localDataSource.insertListIntoDB(list)
        val delivery = list[0]
        val updateDelivery = delivery.copy(isFavorite = !delivery.isFavorite)
        val updatedDelivery = localDataSource.updateDeliveryFavoriteState(updateDelivery)
        Assert.assertFalse(delivery.isFavorite)
        Assert.assertTrue(updatedDelivery.isFavorite)
    }


    @Test
    fun clearDbTest() {
        val list =
            LocalDataGeneratorTest.getDeliveryList()
        localDataSource.insertListIntoDB(list)
        localDataSource.clearDb()
        Assert.assertEquals(0, localDataSource.getDeliveryCount())

    }


    @After
    @Throws(IOException::class)
    fun clean() {
        localDataSource.clearDb()
        db.close()
    }
}