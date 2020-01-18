package com.example.deliveryledger.viewmodel


sealed class EventType

object OnDeliveryItemSelected : EventType()

data class OnNetworkError(val msg: String) : EventType()