package com.example.deliveryledger.view.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.deliveryledger.R
import com.example.deliveryledger.databinding.FragmentDeliveryDetailBinding
import com.example.deliveryledger.di.application.DeliveryApplication
import com.example.deliveryledger.viewmodel.DeliveryActivityViewModel
import com.example.deliveryledger.viewmodel.DeliveryDetailViewModel
import javax.inject.Inject


class DeliveryDetailFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var binding: FragmentDeliveryDetailBinding

    private lateinit var deliveryActivityViewModel: DeliveryActivityViewModel

    private lateinit var deliveryDetailViewModel: DeliveryDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        (activity?.application as DeliveryApplication).appComponent
            .deliveryViewComponent().create().inject(this)
        super.onCreate(savedInstanceState)
        deliveryActivityViewModel = ViewModelProviders.of(activity!!, viewModelFactory).
            get(DeliveryActivityViewModel::class.java)
        deliveryDetailViewModel = ViewModelProviders.of(this, viewModelFactory).
            get(DeliveryDetailViewModel::class.java)

        deliveryActivityViewModel.selectedDeliveryId.observe(this, Observer {
            deliveryDetailViewModel.setSelectedDelivery(it)
        })
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.fragment_delivery_detail,
            container, false)

        binding.addToFavorite.setOnClickListener {
            val delivery = binding.delivery
            if(delivery != null){
                deliveryDetailViewModel
                    .updateDeliveryFavoriteState(delivery.copy(isFavorite = !delivery.isFavorite))
            }
        }

        return binding.root
    }


    override fun onStart() {
        deliveryDetailViewModel.delivery.observe(this, Observer {
            binding.delivery = it
        })
        super.onStart()
    }

    companion object {
        fun newInstance() = DeliveryDetailFragment()
    }
}
