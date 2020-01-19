package com.example.deliveryledger.di.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.deliveryledger.viewmodel.DeliveryActivityViewModel
import com.example.deliveryledger.viewmodel.DeliveryDetailViewModel
import com.example.deliveryledger.viewmodel.DeliveryListViewModel
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
    @ViewModelKey(DeliveryActivityViewModel::class)
    internal abstract fun bindDeliveryActivityVM(viewModel: DeliveryActivityViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(DeliveryDetailViewModel::class)
    internal abstract fun bindDeliveryDetailVM(viewModel: DeliveryDetailViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DeliveryListViewModel::class)
    internal abstract fun bindDeliveryListVM(viewModel: DeliveryListViewModel): ViewModel
}