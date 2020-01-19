package com.example.deliveryledger.di.view

import com.example.deliveryledger.view.activity.DeliveryActivity
import com.example.deliveryledger.view.fragment.DeliveryDetailFragment
import com.example.deliveryledger.view.fragment.DeliveryListFragment
import dagger.Subcomponent

@ActivityScope
@Subcomponent( modules = [DeliveryViewModule::class])
interface DeliveryViewComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): DeliveryViewComponent
    }

    fun inject(activity: DeliveryActivity)
    fun inject(fragment: DeliveryListFragment)
    fun inject(fragment: DeliveryDetailFragment)
}