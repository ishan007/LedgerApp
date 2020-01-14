package com.example.deliveryledger.di.repository

import android.app.Application
import androidx.room.Room
import com.example.deliveryledger.repository.Repository
import com.example.deliveryledger.repository.network.RemoteDataSource
import com.example.deliveryledger.repository.network.RequestApi
import com.example.deliveryledger.repository.storage.DeliveryLedgerDB
import com.example.deliveryledger.repository.storage.LocalDataSource
import com.example.deliveryledger.util.DatabaseConstants
import com.example.deliveryledger.util.NetworkConstants
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideRequestApi(retrofit: Retrofit): RequestApi {
        return retrofit.create(RequestApi::class.java)
    }


    @Provides
    @Singleton
    fun provideRetrofitInstance(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(NetworkConstants.BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(provideOkHttpClient())
            .build()
    }

    @Provides
    @Singleton
    fun provideDeliveryLedgerDB(application: Application): DeliveryLedgerDB{
        return Room.databaseBuilder(
            application,
            DeliveryLedgerDB::class.java, DatabaseConstants.DB_NAME
        ).build()
    }

    private fun provideOkHttpClient(): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
        okHttpClientBuilder.connectTimeout(30, TimeUnit.SECONDS)
        okHttpClientBuilder.readTimeout(30, TimeUnit.SECONDS)
        okHttpClientBuilder.writeTimeout(30, TimeUnit.SECONDS)
        return okHttpClientBuilder.build()
    }

}

