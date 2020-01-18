package com.example.deliveryledger

import com.example.deliveryledger.repository.entities.Delivery
import com.example.deliveryledger.repository.network.RemoteDataSource
import com.example.deliveryledger.repository.network.RequestApi
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito


class RemoteDataSourceTest : BaseUnitTest(){

    @Mock
    private lateinit var requestApi: RequestApi

    @InjectMocks
    private lateinit var remoteDataSource: RemoteDataSource

    @Test
    fun getDeliveryListFromApi(){
        val list = DataGeneratorTest.getDeliveryList()
        Mockito.`when`(requestApi.getDeliveriesList(0, 20))
            .thenReturn(Observable.just(list))
        val observable = remoteDataSource.getDeliveriesListFromAPI(0)
        val testObserver = TestObserver<List<Delivery>>()
        observable.subscribe(testObserver)
        testObserver.assertValue(list)
    }
}