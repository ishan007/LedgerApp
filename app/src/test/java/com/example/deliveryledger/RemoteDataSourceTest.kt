package com.example.deliveryledger

import com.example.deliveryledger.repository.entities.Delivery
import com.example.deliveryledger.repository.network.RemoteDataSource
import com.example.deliveryledger.repository.network.RequestApi
import io.reactivex.Observable
import org.junit.Assert
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito


class RemoteDataSourceTest : BaseUnitTest(){

    @Mock
    private lateinit var requestApi: RequestApi

    private lateinit var remoteDataSource: RemoteDataSource

    override fun setup() {
        super.setup()
        remoteDataSource = RemoteDataSource(requestApi)
    }

    @Test
    fun getDeliveryListFromApi(){
        val list = DataGeneratorTest.getDeliveryList()
        Mockito.`when`(requestApi.getDeliveriesList(0, 20))
            .thenReturn(Observable.just(list))
        val observable = remoteDataSource.getDeliveriesListFromAPI(0)

        val expectedList = arrayListOf<Delivery>()
        observable.subscribe{
            expectedList.addAll(it)
        }

        Assert.assertEquals(list.size, expectedList.size)
    }
}