package com.example.deliveryledger.viewmodel.events


sealed class EventType

object OnDeliveryItemSelected : EventType()

data class OnNetworkError(val msg: String) : EventType()