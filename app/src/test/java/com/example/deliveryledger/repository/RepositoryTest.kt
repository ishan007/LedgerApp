package com.example.deliveryledger.repository

import com.example.deliveryledger.BaseUnitTest
import com.example.deliveryledger.DataGeneratorTest
import com.example.deliveryledger.repository.entities.Delivery
import com.example.deliveryledger.repository.network.RemoteDataSource
import com.example.deliveryledger.repository.storage.LocalDataSource
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import org.junit.After
import org.junit.Assert
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import java.io.IOException


class RepositoryTest : BaseUnitTest(){

    @Mock
    private lateinit var remoteDataSource: RemoteDataSource

    @Mock
    private lateinit var localDataSource: LocalDataSource

    @InjectMocks
    private lateinit var repository: Repository


    @Test
    fun testListFromDB(){
        val list =
            DataGeneratorTest.getDeliveryList()
        val dataSource =
            DataGeneratorTest.getDataSource(
                list
            )

        Mockito.`when`(localDataSource.getDeliveryListDataSource()).thenReturn(dataSource)

        val obtainedDataSource = repository.getDeliveryListDataSource()
        Assert.assertSame(dataSource, obtainedDataSource)
    }


    @Test
    fun testListFromApi(){
        val list =
            DataGeneratorTest.getDeliveryList()
        Mockito.`when`(remoteDataSource.getDeliveriesListFromAPI(0))
            .thenReturn(Observable.just(list))

        val observable = repository.getListFromAPI(0)
        val testObserver = TestObserver<List<Delivery>>()
        observable.subscribe(testObserver)
        testObserver.assertValue(list)
    }


    @Test
    fun testInsertListInDB(){
        val list =
            DataGeneratorTest.getDeliveryList()
        Mockito.`when`(localDataSource.insertListIntoDB(list)).then {
            Unit
        }
        repository.insertListIntoDB(list)
        Mockito.verify(localDataSource, Mockito.times(1)).insertListIntoDB(list)
    }

    @After
    @Throws(IOException::class)
    fun clean(){
        localDataSource.clearDb()
    }
}