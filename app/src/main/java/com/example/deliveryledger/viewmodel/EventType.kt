package com.example.deliveryledger.viewmodel

import com.example.deliveryledger.repository.model.Delivery


sealed class EventType

data class OnItemSelected(val delivery: Delivery) : EventType()

data class OnNetworkError(val msg: String) : EventType()