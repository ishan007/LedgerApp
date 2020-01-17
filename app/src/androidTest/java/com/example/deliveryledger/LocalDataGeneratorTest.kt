package com.example.deliveryledger

import com.example.deliveryledger.repository.entities.Delivery
import com.example.deliveryledger.repository.entities.Route
import com.example.deliveryledger.repository.entities.Sender

class LocalDataGeneratorTest {

    companion object{

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

    }
}