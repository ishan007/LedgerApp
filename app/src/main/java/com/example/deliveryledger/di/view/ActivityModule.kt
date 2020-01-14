package com.example.deliveryledger.di.view

import com.example.deliveryledger.di.viewmodel.ViewModelFactoryModule
import com.example.deliveryledger.view.activity.DeliveryActivity
import com.example.deliveryledger.view.activity.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(modules = [ViewModelFactoryModule::class])
    abstract fun contributeDeliveryActivity(): DeliveryActivity

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity


}