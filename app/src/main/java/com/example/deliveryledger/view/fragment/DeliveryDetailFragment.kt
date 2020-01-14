package com.example.deliveryledger.view.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.deliveryledger.R
import com.example.deliveryledger.databinding.FragmentDeliveryDetailBinding
import com.example.deliveryledger.viewmodel.DeliveryLedgerViewModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject


class DeliveryDetailFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var binding: FragmentDeliveryDetailBinding

    private lateinit var deliveryLedgerViewModel: DeliveryLedgerViewModel

    private val disposable = CompositeDisposable()


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
        super.onStart()
        disposable.add(deliveryLedgerViewModel.selectedDeliverySubject.subscribe {
            binding.delivery = it
        })
    }

    override fun onStop() {
        disposable.clear()
        super.onStop()
    }


    companion object {
        @JvmStatic
        fun newInstance() = DeliveryDetailFragment()
    }
}
