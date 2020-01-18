package com.example.deliveryledger.di.view

import com.example.deliveryledger.di.viewmodel.ViewModelFactoryModule
import com.example.deliveryledger.view.fragment.DeliveryDetailFragment
import com.example.deliveryledger.view.fragment.DeliveryListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentModule {

    @ContributesAndroidInjector( modules = [ViewModelFactoryModule::class] )
    abstract fun contributeMyDeliveriesFragment() : DeliveryListFragment

    @ContributesAndroidInjector( modules = [ViewModelFactoryModule::class] )
    abstract fun contributeDeliveryDetailFragment() : DeliveryDetailFragment

}