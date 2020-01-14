package com.example.deliveryledger

import com.example.deliveryledger.repository.model.Delivery
import com.example.deliveryledger.repository.model.Route
import com.example.deliveryledger.repository.model.Sender

class DataGeneratorTest {

    companion object{

        fun getDeliveryList(): List<Delivery>{
            val list = arrayListOf<Delivery>()
            for(i in 1..10){
                val route = Route("abcd$i", "efgh$i")
                val sender = Sender("test$i","1111$i","abc$i@ghk.com")
                val delivery = Delivery(
                    "123$i",
                    "Test$i", "12:00", "test/abcd",
                    "$123", "$123", route, sender,false)
                list.add(delivery)
            }
            return list
        }

    }
}