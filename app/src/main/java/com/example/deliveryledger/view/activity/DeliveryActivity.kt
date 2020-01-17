package com.example.deliveryledger.view.activity

import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.deliveryledger.R
import com.example.deliveryledger.databinding.ActivityDeliveryBinding
import com.example.deliveryledger.view.fragment.DeliveryDetailFragment
import com.example.deliveryledger.view.fragment.MyDeliveriesFragment
import com.example.deliveryledger.viewmodel.DeliveryLedgerViewModel
import com.example.deliveryledger.viewmodel.OnEvent
import com.example.deliveryledger.viewmodel.OnItemSelected
import com.example.deliveryledger.viewmodel.OnNetworkError
import javax.inject.Inject

class DeliveryActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var onEventObserver: MutableLiveData<OnEvent<*>>

    private lateinit var deliveryLedgerViewModel: DeliveryLedgerViewModel

    private lateinit var binding: ActivityDeliveryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_delivery)
        initFragment()

        deliveryLedgerViewModel = ViewModelProviders.of(this, viewModelFactory).
            get(DeliveryLedgerViewModel::class.java)

        onEventObserver.observe(this, Observer {
            handleEvent(it)
        })

    }


    private fun handleEvent(onEvent: OnEvent<*>){
        when(val event = onEvent.getEvent()){
            is OnItemSelected -> {
                openDetailFragment()
            }

            is OnNetworkError -> {
                Toast.makeText(this, event.msg, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initFragment(){
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, MyDeliveriesFragment.newInstance())
            .commit()
    }

    private fun openDetailFragment(){
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, DeliveryDetailFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }
}
