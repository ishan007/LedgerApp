package com.example.deliveryledger.viewmodel.events

/**
 * Event types with classType as mandatory information used to filter the event.
 * An event can be very specific like attaching different fragment on item selection
 * which no other observer should consume and an event can be generic like network error
 * which can be consumed by any observer to show toast or snake bar. In this app Application
 * type is used as generic event
 *
 */
sealed class EventType(open val classType: Class<*>)

data class OnDeliveryItemSelected(override val classType: Class<*>) : EventType(classType)

data class OnShowLoader(override val classType: Class<*>) : EventType(classType)

data class OnHideLoader(override val classType: Class<*>) : EventType(classType)

data class OnLoadPageError(override val classType: Class<*>) : EventType(classType)