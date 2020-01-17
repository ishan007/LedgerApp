package com.example.deliveryledger.repository.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Delivery (@PrimaryKey val id: String,
                     val remarks: String,
                     val pickupTime: String,
                     val goodsPicture: String,
                     val deliveryFee: String,
                     val surcharge: String,
                     @Embedded val route: Route,
                     @Embedded val sender: Sender,
                     val isFavorite: Boolean)