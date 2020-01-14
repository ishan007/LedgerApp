package com.example.deliveryledger.viewmodel

class OnEvent<out T>(private val event: T) {

    private var hasBeenHandled = false

    fun getEvent(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            event
        }
    }

}