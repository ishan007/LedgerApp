package com.example.deliveryledger.repository.domain.task

import com.example.deliveryledger.repository.Repository
import com.example.deliveryledger.repository.domain.usecase.RefreshDeliveryListUseCase
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RefreshDeliveryListTask @Inject constructor(private val repository: Repository) :
    RefreshDeliveryListUseCase{

    override fun refreshData(): Observable<Unit> {
        return repository.getListFromAPI(0).map {
                repository.clearDb()
                repository.insertListIntoDB(it)
            }. subscribeOn(Schedulers.io())
    }

}