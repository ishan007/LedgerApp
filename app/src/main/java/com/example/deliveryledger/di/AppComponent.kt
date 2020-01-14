package com.example.deliveryledger.di

import android.app.Application
import com.example.deliveryledger.di.repository.RepositoryModule
import com.example.deliveryledger.di.view.ActivityModule
import com.example.deliveryledger.di.view.FragmentModule
import com.example.deliveryledger.di.viewmodel.ViewModelFactoryModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class,
        AppModule::class,
        RepositoryModule::class,
        ViewModelFactoryModule::class,
        ActivityModule::class,
        FragmentModule::class]
)
interface AppComponent : AndroidInjector<DeliveryApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}