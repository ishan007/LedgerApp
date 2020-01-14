package com.example.deliveryledger

import android.content.Context
import androidx.room.Room
import androidx.room.paging.LimitOffsetDataSource
import androidx.test.platform.app.InstrumentationRegistry
import com.example.deliveryledger.repository.Repository
import com.example.deliveryledger.repository.model.Delivery
import com.example.deliveryledger.repository.network.RemoteDataSource
import com.example.deliveryledger.repository.network.RequestApi
import com.example.deliveryledger.repository.storage.DeliveryLedgerDB
import com.example.deliveryledger.repository.storage.LocalDataSource
import io.reactivex.Observable
import org.junit.After
import org.junit.Assert
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import java.io.IOException


class RepositoryTest : BaseUnitTest(){

    @Mock
    private lateinit var requestApi: RequestApi

    @InjectMocks
    private lateinit var remoteDataSource: RemoteDataSource

    private lateinit var repository: Repository

    private lateinit var db: DeliveryLedgerDB

    private lateinit var appContext: Context

    private lateinit var localDataSource: LocalDataSource

    override fun setup() {
        super.setup()

        appContext = InstrumentationRegistry.getInstrumentation().targetContext

        db = Room.inMemoryDatabaseBuilder(appContext, DeliveryLedgerDB::class.java)
            .allowMainThreadQueries().build()
        localDataSource = LocalDataSource(db)

        remoteDataSource = RemoteDataSource(requestApi)

        repository = Repository(localDataSource, remoteDataSource)
    }

    @Test
    fun testRepo(){
        val list = DataGeneratorTest.getDeliveryList()

        Mockito.`when`(requestApi.getDeliveriesList(0,20))
            .thenReturn(Observable.just(list))

        val serverResponseList = arrayListOf<Delivery>()

        repository.getListFromAPI(0).subscribe{
            serverResponseList.addAll(it)
        }

        repository.insertListIntoDB(serverResponseList)

        val itemCount = (repository.getDeliveryListDataSource().create() as LimitOffsetDataSource)
            .countItems()

        Assert.assertEquals(list.size, itemCount)
        Assert.assertEquals(list.size, repository.getDeliveryCount())

    }

    @After
    @Throws(IOException::class)
    fun clean(){
        localDataSource.clearDb()
        db.close()
    }
}