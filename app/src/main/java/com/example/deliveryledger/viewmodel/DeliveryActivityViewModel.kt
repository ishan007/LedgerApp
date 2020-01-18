package com.example.deliveryledger.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject


class DeliveryActivityViewModel @Inject constructor(): ViewModel(){


    private val _selectedDeliveryId = MutableLiveData<String>()
    val selectedDeliveryId : LiveData<String> = _selectedDeliveryId

    fun onDeliverySelected(deliveryId: String){
        _selectedDeliveryId.value = deliveryId
    }

}