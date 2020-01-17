package com.example.deliveryledger

import android.content.Context
import androidx.room.Room
import androidx.room.paging.LimitOffsetDataSource
import androidx.test.platform.app.InstrumentationRegistry
import com.example.deliveryledger.repository.entities.Delivery
import com.example.deliveryledger.repository.storage.DeliveryLedgerDB
import com.example.deliveryledger.repository.storage.LocalDataSource
import io.reactivex.observers.TestObserver
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.io.IOException
import java.util.concurrent.TimeUnit

class LocalDataSourceInstrumentTest : BaseInstrumentedTest() {

    private lateinit var db: DeliveryLedgerDB
    private lateinit var appContext: Context
    private lateinit var localDataSource: LocalDataSource


    @Before
    fun setup(){
        appContext = InstrumentationRegistry.getInstrumentation().targetContext
        db = Room.inMemoryDatabaseBuilder(appContext, DeliveryLedgerDB::class.java).build()
        localDataSource = LocalDataSource(db)
    }

    @Test
    fun insertData(){
        val list = LocalDataGeneratorTest.getDeliveryList()
        localDataSource.insertListIntoDB(list)
        val itemCount = (localDataSource.getDeliveryListDataSource().create() as LimitOffsetDataSource).countItems()
        Assert.assertEquals(list.size, itemCount)
        Assert.assertEquals(list.size, localDataSource.getDeliveryCount())
    }


    @Test
    fun updateData(){
        val list = LocalDataGeneratorTest.getDeliveryList()
        localDataSource.insertListIntoDB(list)
        val delivery = list[0]
        val observable = localDataSource.updateDeliveryFavoriteState(delivery.id, true)
        val testObserver = TestObserver<Delivery>()
        observable.subscribe(testObserver)
        testObserver.await(3, TimeUnit.SECONDS)
        testObserver.assertValue(delivery.copy(isFavorite = true))
    }


    @Test
    fun clearDbTest(){
        val list = LocalDataGeneratorTest.getDeliveryList()
        localDataSource.insertListIntoDB(list)
        localDataSource.clearDb()
        Assert.assertEquals(0, localDataSource.getDeliveryCount())

    }


    @After
    @Throws(IOException::class)
    fun clean(){
        localDataSource.clearDb()
        db.close()
    }
}