package com.example.deliveryledger.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.deliveryledger.BaseUnitTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks


class DeliveryActivityViewModelTest : BaseUnitTest() {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @InjectMocks
    lateinit var deliveryActivityViewModel: DeliveryActivityViewModel


    @Test
    fun testOnDeliverySelected(){
        val deliveryId = "123"
        deliveryActivityViewModel.onDeliverySelected(deliveryId)
        Assert.assertEquals(deliveryId, deliveryActivityViewModel.selectedDeliveryId.value)
    }
}