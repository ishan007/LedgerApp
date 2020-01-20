package com.example.deliveryledger.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import com.example.deliveryledger.BaseUnitTest
import com.example.deliveryledger.DataGeneratorTest
import com.example.deliveryledger.repository.DeliveryDataBoundaryCallback
import com.example.deliveryledger.repository.domain.usecase.DeliveryListUseCase
import com.example.deliveryledger.repository.domain.usecase.RefreshDeliveryListUseCase
import com.example.deliveryledger.repository.entities.Delivery
import com.example.deliveryledger.util.NetworkConstants
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import java.io.IOException


class DeliveryListViewModelTest : BaseUnitTest() {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var deliveryListUseCase: DeliveryListUseCase

    @Mock
    lateinit var refreshDeliveryListUseCase: RefreshDeliveryListUseCase

    @Mock
    lateinit var boundaryCallback: DeliveryDataBoundaryCallback

    @InjectMocks
    lateinit var deliveryListViewModel: DeliveryListViewModel


    @Test
    fun testDeliveryList(){
        val list =
            DataGeneratorTest.getDeliveryList()
        val pagedListBuilder = getRxPagedListBuilder(list)

        Mockito.`when`(deliveryListUseCase.getDeliveries()).thenReturn(pagedListBuilder)
        val deliveryListObservable = deliveryListViewModel.deliveryList

        val testObserver = TestObserver<PagedList<Delivery>>()
        deliveryListObservable.subscribe(testObserver)
        testObserver.assertSubscribed()
        testObserver.awaitCount(1)
        testObserver.assertValueCount(1)
        testObserver.assertValue {
            it.size == list.size
        }
        testObserver.dispose()

    }


    @Test
    fun testRefreshData(){
        val testObserver = TestObserver<Unit>()
        val successOnRefreshData = Observable.just(Unit)
        Mockito.`when`(refreshDeliveryListUseCase.refreshData()).thenReturn(successOnRefreshData)

        val refreshDataResult = deliveryListViewModel.refreshData()
        refreshDataResult.subscribe(testObserver)
        testObserver.awaitCount(1)
        testObserver.assertSubscribed()
        testObserver.assertNoErrors()
        testObserver.assertComplete()
        testObserver.dispose()

    }

    @Test
    fun testExceptionOnRefreshData(){
        val testObserver = TestObserver<Unit>()

        val errorOnRefreshData = Observable.error<Unit>(IOException())
        Mockito.`when`(refreshDeliveryListUseCase.refreshData()).thenReturn(errorOnRefreshData)
        val refreshDataResult = deliveryListViewModel.refreshData()

        refreshDataResult.subscribe(testObserver)
        testObserver.awaitCount(1)
        testObserver.assertSubscribed()
        testObserver.assertError(IOException::class.java)
        testObserver.dispose()
    }

    private fun getRxPagedListBuilder(list: List<Delivery>): RxPagedListBuilder<Int, Delivery>{
        val builder =  PagedList.Config.Builder()
        builder.setEnablePlaceholders(false)
        builder.setPageSize(NetworkConstants.PAGE_LIMIT)
        return RxPagedListBuilder<Int, Delivery>(
            DataGeneratorTest.getDataSource(
                list
            ),
            builder.build()
        )
    }

}