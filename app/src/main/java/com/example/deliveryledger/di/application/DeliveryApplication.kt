package com.example.deliveryledger.di.application

import android.app.Application
import com.example.deliveryledger.di.application.component.DaggerAppComponent

class DeliveryApplication : Application() {

    val appComponent = DaggerAppComponent.builder().application(this).build()

}