package com.example.deliveryledger.repository.storage

import androidx.paging.DataSource
import androidx.room.*
import com.example.deliveryledger.repository.entities.Delivery

@Dao
interface DeliveryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDeliveryEntry(deliveries: List<Delivery> )

    @Query("SELECT * FROM Delivery")
    fun getDeliveryList(): DataSource.Factory<Int, Delivery>

    @Query("SELECT COUNT(id) FROM Delivery")
    fun getDeliveryCount(): Int

    @Update
    fun updateDeliveryFavoriteState(delivery: Delivery)

    @Query("SELECT * FROM Delivery WHERE id = :id")
    fun getDelivery(id: String): Delivery

}