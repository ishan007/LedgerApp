package com.example.deliveryledger.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.deliveryledger.di.view.ActivityScope
import javax.inject.Inject

/**
 * This is a shared view model between fragments of delivery activity.
 * This approach provides us benefit like activity does not need to know about the communication
 * between fragments.
 * Fragments don't need to know anything about each other beside the shared view model contract
 * For example- on phone, list and detail fragments will be visible one by one but on tab we
 * design to make list and detail fragment both visible at same time. In both cases this approach
 * will benefit
 */
@ActivityScope
class DeliveryActivityViewModel @Inject constructor(): ViewModel(){


    private val _selectedDeliveryId = MutableLiveData<String>()
    val selectedDeliveryId : LiveData<String> = _selectedDeliveryId

    fun onDeliverySelected(deliveryId: String){
        _selectedDeliveryId.value = deliveryId
    }

}