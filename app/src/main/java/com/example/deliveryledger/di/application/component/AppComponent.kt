package com.example.deliveryledger.di.application.component

import android.app.Application
import com.example.deliveryledger.di.application.module.AppModule
import com.example.deliveryledger.di.repository.module.RepositoryModule
import com.example.deliveryledger.di.view.DeliveryViewComponent
import com.example.deliveryledger.di.view.DeliveryViewModule
import com.example.deliveryledger.view.activity.SplashActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DeliveryViewModule::class, RepositoryModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun deliveryViewComponent(): DeliveryViewComponent.Factory

    fun inject(activity: SplashActivity)

}