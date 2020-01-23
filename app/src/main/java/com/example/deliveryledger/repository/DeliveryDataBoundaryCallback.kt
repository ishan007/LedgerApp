package com.example.deliveryledger.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.example.deliveryledger.repository.domain.usecase.PageLoadUseCase
import com.example.deliveryledger.repository.entities.Delivery
import com.example.deliveryledger.repository.network.InternetConnectionException
import com.example.deliveryledger.util.NetworkConstants
import com.example.deliveryledger.util.NetworkUtil
import com.example.deliveryledger.util.Logger
import com.example.deliveryledger.view.fragment.DeliveryListFragment
import com.example.deliveryledger.viewmodel.events.*
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject

/**
 * Callback provided by paging library
 */
class DeliveryDataBoundaryCallback @Inject constructor(
    private val pageLoadUseCase: PageLoadUseCase,
    private val disposable: CompositeDisposable,
    private val onEventObserver: MutableLiveData<OnEvent<*>>,
    private val networkUtil: NetworkUtil
) : PagedList.BoundaryCallback<Delivery>(){


    enum class BoundaryState{
        INITIAL_ITEM_LOADED, END_ITEM_LOADED
    }

    override fun onZeroItemsLoaded() {
        Logger.d("OnZero Item loaded")
        loadData(BoundaryState.INITIAL_ITEM_LOADED)
    }

    override fun onItemAtEndLoaded(itemAtEnd: Delivery) {
        Logger.d("On next Item loaded")
        loadData(BoundaryState.END_ITEM_LOADED)
    }

    fun retry(){
        loadData(BoundaryState.END_ITEM_LOADED)
    }


    private fun loadData(state: BoundaryState){
        pageLoadUseCase.loadData(state).doOnSubscribe {
            if(!networkUtil.isNetworkConnected()){

                // throwing exception to control flow is considered anti-pattern
                // therefore throwing custom exception for specific purpose
                throw InternetConnectionException()
            }
            disposable.add(it)
        }.subscribe(observer)
    }

    private val observer = object : DeliveryObserver<Int>(onEventObserver, disposable){

        private var loadCompleted = false

        override fun onSubscribe(d: Disposable) {
            super.onSubscribe(d)
            onEventObserver.postValue(OnEvent(OnShowLoader(DeliveryListFragment::class.java)))
        }

        override fun onNext(t: Int) {
            super.onNext(t)
            if(t < NetworkConstants.PAGE_LIMIT){
                loadCompleted = true
            }
        }


        override fun onComplete() {
            super.onComplete()
            onEventObserver.postValue(OnEvent(OnHideLoader(DeliveryListFragment::class.java, loadCompleted)))
        }
    }


    fun clear(){
        disposable.clear()
    }


}