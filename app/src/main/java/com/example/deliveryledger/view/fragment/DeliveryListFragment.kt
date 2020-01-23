package com.example.deliveryledger.view.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.deliveryledger.R
import com.example.deliveryledger.databinding.FragmentDeliveryListBinding
import com.example.deliveryledger.di.application.DeliveryApplication
import com.example.deliveryledger.util.Logger
import com.example.deliveryledger.view.activity.DeliveryActivity
import com.example.deliveryledger.view.adapter.DeliveryListAdapter
import com.example.deliveryledger.viewmodel.DeliveryActivityViewModel
import com.example.deliveryledger.viewmodel.DeliveryListViewModel
import com.example.deliveryledger.viewmodel.events.*
import com.google.android.material.snackbar.Snackbar
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
            onEventObserver.value =
                OnEvent(OnDeliveryItemSelected(DeliveryActivity::class.java))
        })
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        (activity?.application as DeliveryApplication).appComponent
            .deliveryViewComponent().create().inject(this)
        super.onCreate(savedInstanceState)

        deliveryListViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(DeliveryListViewModel::class.java)

        //{@link com.example.deliveryledger.viewmodel.DeliveryActivityViewModel}
        deliveryActivityViewModel = ViewModelProviders.of(activity!!, viewModelFactory)
            .get(DeliveryActivityViewModel::class.java)

        onEventObserver.observe(this, Observer {
            val eventType = it.peekEvent() as EventType
            //handle events specific to this fragment as generic events will be handled by
            //activity on which this fragment is attached
            if(eventType.classType == DeliveryListFragment::class.java){
                Logger.d("Event on list fragment : $eventType")
                handleEvent(it)
            }
        })
    }


    /**
     *  Handles events, it may be network error or any other event
     */
    private fun handleEvent(onEvent: OnEvent<*>){
        when(val event = onEvent.consumeEvent()){
            is OnShowLoader -> {
                binding.loader.visibility = View.VISIBLE
            }

            is OnHideLoader -> {
                binding.loader.visibility = View.GONE
                if(event.loadCompleted){
                    showPageLoadCompletion()
                }
            }

            is OnLoadPageError -> {
                binding.loader.visibility = View.GONE
                showRetryError()
            }

            is OnNoInternetConnectionError -> {
                showNoInternetConnectionError()
            }
        }
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.fragment_delivery_list,
            container, false
        )

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

    private fun initDeliveryListAdapter() {
        binding.deliveryRv.adapter = adapter
        val layoutManager = LinearLayoutManager(context)
        binding.deliveryRv.layoutManager = layoutManager
    }


    // allows user to refresh delivery list on swipe gesture
    private fun initSwipeRefresh() {
        binding.swipeRefreshView.setOnRefreshListener {
            deliveryListViewModel.refreshData()
                .subscribe(object : DeliveryObserver<Unit>(onEventObserver, disposable) {
                    override fun onComplete() {
                        binding.swipeRefreshView.isRefreshing = false
                    }

                    override fun onError(e: Throwable) {
                        binding.swipeRefreshView.isRefreshing = false
                        showRefreshError()
                    }
                })
        }
    }

    private fun showRetryError(){
        Snackbar.make(
            binding.coordinatorLayout,
            getString(R.string.load_page_error),
            Snackbar.LENGTH_INDEFINITE
        ).setAction(getString(R.string.retry)) {
            deliveryListViewModel.retry()
        }.show()
    }

    private fun showRefreshError(){
        Snackbar.make(
            binding.coordinatorLayout,
            getString(R.string.refresh_error),
            Snackbar.LENGTH_SHORT
        ).show()
    }


    private fun showPageLoadCompletion(){
        Snackbar.make(
            binding.coordinatorLayout,
            getString(R.string.page_load_completed),
            Snackbar.LENGTH_SHORT
        ).show()
    }

    private fun showNoInternetConnectionError(){
        Snackbar.make(
            binding.coordinatorLayout,
            getString(R.string.no_internet_connection),
            Snackbar.LENGTH_INDEFINITE
        ).setAction(getString(R.string.retry)) {
            deliveryListViewModel.retry()
        }.show()
    }



    companion object {
        /**
         * Use this factory method to create a new instance of MyDeliveriesFragment
         * @return A new instance of fragment MyDeliveriesFragment.
         */
        fun newInstance() = DeliveryListFragment()
    }
}
