package com.example.deliveryledger.di.repository.module

import com.example.deliveryledger.repository.domain.task.*
import com.example.deliveryledger.repository.domain.usecase.*
import dagger.Binds
import dagger.Module


@Module
abstract class DomainModule {

    @Binds
    abstract fun bindDeliveryDetailUseCase(getDeliverDetailTask: GetDeliverDetailTask)
            : DeliveryDetailUseCase

    @Binds
    abstract fun bindDeliveryListUseCase(getDeliveryListTask: GetDeliveryListTask)
            : DeliveryListUseCase

    @Binds
    abstract fun bindPageLoadUseCase(pageLoadTask: PageLoadTask)
            : PageLoadUseCase

    @Binds
    abstract fun bindRefreshDeliveryListUseCase(refreshDeliveryListTask: RefreshDeliveryListTask)
            : RefreshDeliveryListUseCase

    @Binds
    abstract fun bindUpdateDeliveryDetailUseCase(updateDeliveryDetailTask: UpdateDeliveryDetailTask)
            : UpdateDeliveryDetailUseCase
}