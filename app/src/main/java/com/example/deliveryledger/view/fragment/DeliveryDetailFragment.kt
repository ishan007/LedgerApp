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
import com.example.deliveryledger.viewmodel.DeliveryLedgerViewModel
import javax.inject.Inject


class DeliveryDetailFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var binding: FragmentDeliveryDetailBinding

    private lateinit var deliveryLedgerViewModel: DeliveryLedgerViewModel

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
            R.layout.fragment_delivery_detail,
            container, false)

        binding.addToFavorite.setOnClickListener {
            deliveryLedgerViewModel.updateDeliveryFavoriteState(binding.delivery!!)
        }

        return binding.root
    }


    override fun onStart() {
        deliveryLedgerViewModel.selectedDelivery.observe(this, Observer {
            binding.delivery = it
        })
        super.onStart()
    }

    companion object {
        fun newInstance() = DeliveryDetailFragment()
    }
}
