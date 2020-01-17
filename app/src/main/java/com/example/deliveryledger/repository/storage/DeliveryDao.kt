package com.example.deliveryledger.repository.storage

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.deliveryledger.repository.entities.Delivery

@Dao
interface DeliveryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDeliveryEntry(deliveries: List<Delivery> )

    @Query("SELECT * FROM Delivery")
    fun getDeliveryList(): DataSource.Factory<Int, Delivery>

    @Query("SELECT COUNT(id) FROM Delivery")
    fun getDeliveryCount(): Int

    @Query("UPDATE Delivery SET isFavorite = :isFavorite WHERE id =:id")
    fun updateDeliveryFavoriteState(id: String, isFavorite: Boolean)

    @Query("SELECT * FROM Delivery WHERE id = :id")
    fun getDelivery(id: String): Delivery

}