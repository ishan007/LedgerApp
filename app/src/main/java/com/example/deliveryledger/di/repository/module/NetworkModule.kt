package com.example.deliveryledger.di.repository.module

import android.app.Application
import com.example.deliveryledger.BuildConfig
import com.example.deliveryledger.repository.network.RequestApi
import com.example.deliveryledger.util.NetworkConstants
import com.example.deliveryledger.util.NetworkUtil
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
class NetworkModule{

    @Provides
    @Singleton
    fun provideRequestApi(retrofit: Retrofit): RequestApi {
        return retrofit.create(RequestApi::class.java)
    }

    @Singleton
    @Provides
    fun provideNetworkUtil(application: Application): NetworkUtil {
        return NetworkUtil(application)
    }


    @Provides
    @Singleton
    fun provideRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(provideOkHttpClient())
            .build()
    }

    private fun provideOkHttpClient(): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
        okHttpClientBuilder.connectTimeout(NetworkConstants.CONNECTION_TIMEOUT, TimeUnit.SECONDS)
        okHttpClientBuilder.readTimeout(NetworkConstants.CONNECTION_TIMEOUT, TimeUnit.SECONDS)
        okHttpClientBuilder.writeTimeout(NetworkConstants.CONNECTION_TIMEOUT, TimeUnit.SECONDS)
        return okHttpClientBuilder.build()
    }

}