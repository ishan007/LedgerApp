package com.example.deliveryledger.di

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class DeliveryApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication>? {
        return DaggerAppComponent.builder().application(this).build()
    }

}