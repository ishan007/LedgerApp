package com.example.deliveryledger.viewmodel.events

/**
 * Event wrapper class to handle single events using live data
 */
class OnEvent<out T>(private val event: T) {

    private var hasBeenHandled = false

    /**
     * Consume event only if it is not handled
     * so that no other observer can act accidentally on this event
     */
    fun consumeEvent(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            event
        }
    }

    /**
     * Observer can peek event before consuming it
     */
    fun peekEvent(): T = event

}