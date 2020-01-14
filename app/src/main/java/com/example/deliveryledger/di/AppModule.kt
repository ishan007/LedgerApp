package com.example.deliveryledger.di

import android.app.Application
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.example.deliveryledger.R
import com.example.deliveryledger.viewmodel.OnEvent
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideRequestOptions(): RequestOptions{
        return RequestOptions
            .placeholderOf(R.mipmap.ic_launcher)
            .error(R.mipmap.ic_launcher)
    }

    @Singleton
    @Provides
    fun provideGlideInstance(application: Application, requestOptions: RequestOptions): RequestManager {
        return Glide.with(application)
            .setDefaultRequestOptions(requestOptions)
    }

    @Provides
    fun provideDisposable(): CompositeDisposable{
        return CompositeDisposable()
    }

    @Singleton
    @Provides
    fun provideOnEventObservable(): PublishSubject<OnEvent<*>> {
        return PublishSubject.create()
    }

}