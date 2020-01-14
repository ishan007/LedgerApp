package com.example.deliveryledger.view.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.deliveryledger.R
import com.example.deliveryledger.databinding.FragmentMyDeliveriesBinding
import com.example.deliveryledger.view.adapter.DeliveryListAdapter
import com.example.deliveryledger.viewmodel.DeliveryObserver
import com.example.deliveryledger.viewmodel.DeliveryLedgerViewModel
import com.example.deliveryledger.viewmodel.OnEvent
import com.example.deliveryledger.viewmodel.OnItemSelected
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject


class MyDeliveriesFragment : BaseFragment() {

    private lateinit var binding: FragmentMyDeliveriesBinding

    private lateinit var deliveryLedgerViewModel: DeliveryLedgerViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var disposable: CompositeDisposable

    @Inject
    lateinit var onErrorObserver: PublishSubject<OnEvent<*>>

    private val adapter: DeliveryListAdapter by lazy {
        DeliveryListAdapter(DeliveryListAdapter.DeliverySelectionListener {
            deliveryLedgerViewModel.onDeliverySelected(it)
            deliveryLedgerViewModel.getEventObserver().onNext(OnEvent(OnItemSelected(it)))
        })
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        deliveryLedgerViewModel = ViewModelProviders.of(activity!!, viewModelFactory).
            get(DeliveryLedgerViewModel::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.fragment_my_deliveries,
            container, false)

        initDeliveryListAdapter()
        initSwipeRefresh()

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        disposable.add(deliveryLedgerViewModel.deliveryList.subscribe(adapter::submitList))
    }

    override fun onStop() {
        disposable.clear()
        super.onStop()
    }

    private fun initDeliveryListAdapter(){
        binding.deliveryRv.adapter = adapter
        val layoutManager = LinearLayoutManager(context)
        binding.deliveryRv.layoutManager = layoutManager
        binding.deliveryRv.addItemDecoration(DividerItemDecoration(context, layoutManager.orientation))
    }


    private fun initSwipeRefresh(){
        binding.swipeRefreshView.setOnRefreshListener {
            deliveryLedgerViewModel.refreshData()
                .subscribeOn(Schedulers.io()).subscribe(object : DeliveryObserver<Unit>(onErrorObserver, disposable){
                    override fun onComplete() {
                        binding.swipeRefreshView.isRefreshing = false
                    }

                    override fun onError(e: Throwable) {
                        binding.swipeRefreshView.isRefreshing = false
                        super.onError(e)
                    }
                })
        }
    }


    companion object {
        /**
         * Use this factory method to create a new instance of MyDeliveriesFragment
         * @return A new instance of fragment MyDeliveriesFragment.
         */
        @JvmStatic
        fun newInstance() = MyDeliveriesFragment()
    }
}
