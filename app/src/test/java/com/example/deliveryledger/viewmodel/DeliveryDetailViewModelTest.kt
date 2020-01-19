package com.example.deliveryledger.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.example.deliveryledger.BaseUnitTest
import com.example.deliveryledger.DataGeneratorTest
import com.example.deliveryledger.repository.domain.usecase.DeliveryDetailUseCase
import com.example.deliveryledger.repository.domain.usecase.UpdateDeliveryDetailUseCase
import com.example.deliveryledger.viewmodel.events.OnEvent
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito


class DeliveryDetailViewModelTest : BaseUnitTest() {


    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var deliveryDetailUseCase: DeliveryDetailUseCase

    @Mock
    lateinit var updateDeliveryDetailUseCase: UpdateDeliveryDetailUseCase

    @Mock
    lateinit var disposable: CompositeDisposable

    @Mock
    lateinit var onEventObserver: MutableLiveData<OnEvent<*>>

    @InjectMocks
    lateinit var deliveryDetailViewModel: DeliveryDetailViewModel


    @Test
    fun testUpdateFeature(){
        val delivery =
            DataGeneratorTest.getDelivery()
        var updateDelivery = delivery.copy(isFavorite = true)

        Mockito.`when`(updateDeliveryDetailUseCase.updateDeliveryFavoriteState(updateDelivery))
            .thenReturn(Observable.just(updateDelivery))

        deliveryDetailViewModel.updateDeliveryFavoriteState(updateDelivery)

        Assert.assertTrue(deliveryDetailViewModel.delivery.value?.isFavorite ?: false)

        updateDelivery = delivery.copy(isFavorite = false)

        Mockito.`when`(updateDeliveryDetailUseCase.updateDeliveryFavoriteState(updateDelivery))
            .thenReturn(Observable.just(updateDelivery))

        deliveryDetailViewModel.updateDeliveryFavoriteState(updateDelivery)

        Assert.assertFalse(deliveryDetailViewModel.delivery.value?.isFavorite ?: true)
    }


    @Test
    fun testSetSelectedDelivery(){
        val delivery =
            DataGeneratorTest.getDelivery()

        Mockito.`when`(deliveryDetailUseCase.getDeliveryDetail(delivery.id))
            .thenReturn(Observable.just(delivery))

        deliveryDetailViewModel.setSelectedDelivery(delivery.id)

        Assert.assertEquals(deliveryDetailViewModel.delivery.value?.id, delivery.id)


        Mockito.`when`(deliveryDetailUseCase.getDeliveryDetail("INVALID_ID"))
            .thenReturn(Observable.empty())

        deliveryDetailViewModel.setSelectedDelivery("INVALID_ID")

        Assert.assertEquals(deliveryDetailViewModel.delivery.value?.id, delivery.id)

    }


}