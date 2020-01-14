package com.example.deliveryledger.di.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.deliveryledger.viewmodel.DeliveryLedgerViewModel
import com.example.deliveryledger.viewmodel.ViewModelProviderFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelProviderFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(DeliveryLedgerViewModel::class)
    internal abstract fun postListViewModel(ledgerViewModel: DeliveryLedgerViewModel): ViewModel
}