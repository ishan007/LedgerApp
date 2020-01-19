package com.example.deliveryledger

import androidx.paging.DataSource
import androidx.paging.PositionalDataSource
import com.example.deliveryledger.repository.entities.Delivery
import com.example.deliveryledger.repository.entities.Route
import com.example.deliveryledger.repository.entities.Sender

class DataGeneratorTest {

    companion object{

        fun getDelivery(): Delivery{
            val route = Route("From", "To")
            val sender = Sender("Test","1111","abc@ghk.com")
            return Delivery("123", "Test remarks",
                "12:00", "test/pic",
                "$123", "$123",
                route, sender,false)
        }

        fun getDeliveryList(): List<Delivery>{
            val list = arrayListOf<Delivery>()
            for(i in 1..10){
                val route = Route("From$i", "To$i")
                val sender = Sender("Test$i","1111$i","abc$i@ghk.com")
                val delivery = Delivery(
                    "123$i",
                    "Test remarks$i", "12:00", "test/pic",
                    "$123", "$123", route, sender,false)
                list.add(delivery)
            }
            return list
        }


        fun getDataSource(list: List<Delivery>): DataSource.Factory<Int, Delivery>{
            return object : DataSource.Factory<Int, Delivery>(){
                override fun create(): DataSource<Int, Delivery> {
                    return object : PositionalDataSource<Delivery>(){
                        override fun loadRange(
                            params: LoadRangeParams,
                            callback: LoadRangeCallback<Delivery>
                        ) {}

                        override fun loadInitial(params: LoadInitialParams,
                                                 callback: LoadInitialCallback<Delivery>
                        ) {
                            callback.onResult(list,0)
                        }
                    }
                }
            }
        }



    }
}