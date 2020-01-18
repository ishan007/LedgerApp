package com.example.deliveryledger.view.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.deliveryledger.R
import com.example.deliveryledger.databinding.FragmentDeliveryListBinding
import com.example.deliveryledger.view.adapter.DeliveryListAdapter
import com.example.deliveryledger.viewmodel.*
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject


class DeliveryListFragment : BaseFragment() {

    private lateinit var binding: FragmentDeliveryListBinding

    private lateinit var deliveryListViewModel: DeliveryListViewModel

    private lateinit var deliveryActivityViewModel: DeliveryActivityViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var disposable: CompositeDisposable

    @Inject
    lateinit var onEventObserver: MutableLiveData<OnEvent<*>>


    private val adapter: DeliveryListAdapter by lazy {
        DeliveryListAdapter(DeliveryListAdapter.DeliverySelectionListener {
            deliveryActivityViewModel.onDeliverySelected(it.id)
            onEventObserver.value = OnEvent(OnDeliveryItemSelected)
        })
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        deliveryListViewModel = ViewModelProviders.of(this, viewModelFactory).
            get(DeliveryListViewModel::class.java)
        deliveryActivityViewModel = ViewModelProviders.of(activity!!, viewModelFactory).
            get(DeliveryActivityViewModel::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.fragment_delivery_list,
            container, false)

        initDeliveryListAdapter()
        initSwipeRefresh()

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        disposable.add(deliveryListViewModel.deliveryList.subscribe(adapter::submitList))
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
            deliveryListViewModel.refreshData()
                .subscribe(object : DeliveryObserver<Unit>(onEventObserver, disposable){
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
        fun newInstance() = DeliveryListFragment()
    }
}
