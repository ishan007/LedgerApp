package com.example.deliveryledger.di

import androidx.lifecycle.MutableLiveData
import com.example.deliveryledger.viewmodel.OnEvent
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    fun provideDisposable(): CompositeDisposable{
        return CompositeDisposable()
    }

    @Singleton
    @Provides
    fun provideOnEventObservable(): MutableLiveData<OnEvent<*>> {
        return MutableLiveData()
    }

}