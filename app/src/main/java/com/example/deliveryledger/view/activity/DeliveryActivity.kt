package com.example.deliveryledger.view.activity

import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.deliveryledger.R
import com.example.deliveryledger.databinding.ActivityDeliveryBinding
import com.example.deliveryledger.view.fragment.DeliveryDetailFragment
import com.example.deliveryledger.view.fragment.DeliveryListFragment
import com.example.deliveryledger.viewmodel.OnDeliveryItemSelected
import com.example.deliveryledger.viewmodel.OnEvent
import com.example.deliveryledger.viewmodel.OnNetworkError
import javax.inject.Inject

class DeliveryActivity : BaseActivity() {

    @Inject
    lateinit var onEventObserver: MutableLiveData<OnEvent<*>>

    private lateinit var binding: ActivityDeliveryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_delivery)
        initFragment()

        onEventObserver.observe(this, Observer {
            handleEvent(it)
        })

    }


    private fun handleEvent(onEvent: OnEvent<*>){
        when(val event = onEvent.getEvent()){
            is OnDeliveryItemSelected -> {
                openDetailFragment()
            }

            is OnNetworkError -> {
                Toast.makeText(this, event.msg, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initFragment(){
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, DeliveryListFragment.newInstance())
            .commit()
    }

    private fun openDetailFragment(){
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, DeliveryDetailFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }
}
