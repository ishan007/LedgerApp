package com.example.deliveryledger.di.repository

import com.example.deliveryledger.repository.domain.GetDeliverDetailTask
import com.example.deliveryledger.repository.domain.GetDeliveryListTask
import com.example.deliveryledger.repository.domain.PageLoadTask
import com.example.deliveryledger.repository.domain.RefreshDeliveryListTask
import com.example.deliveryledger.repository.domain.base.DeliveryDetailUseCase
import com.example.deliveryledger.repository.domain.base.DeliveryListUseCase
import com.example.deliveryledger.repository.domain.base.PageLoadUseCase
import com.example.deliveryledger.repository.domain.base.RefreshDeliveryListUseCase
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
}