package com.example.deliveryledger.view.activity

import android.app.Application
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.deliveryledger.R
import com.example.deliveryledger.databinding.ActivityDeliveryBinding
import com.example.deliveryledger.di.application.DeliveryApplication
import com.example.deliveryledger.util.Util
import com.example.deliveryledger.view.fragment.DeliveryDetailFragment
import com.example.deliveryledger.view.fragment.DeliveryListFragment
import com.example.deliveryledger.viewmodel.events.EventType
import com.example.deliveryledger.viewmodel.events.OnDeliveryItemSelected
import com.example.deliveryledger.viewmodel.events.OnEvent
import com.example.deliveryledger.viewmodel.events.OnNoInternetConnectionError
import javax.inject.Inject

/**
 * Delivery activity which shows this list of deliveries and their details
 */
class DeliveryActivity : BaseActivity(), FragmentManager.OnBackStackChangedListener {

    @Inject
    lateinit var onEventObserver: MutableLiveData<OnEvent<*>>

    private lateinit var binding: ActivityDeliveryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as DeliveryApplication).appComponent
            .deliveryViewComponent().create().inject(this)
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_delivery)

        onEventObserver.observe(this, Observer {
            val eventType = it.peekEvent() as EventType
            //handle event only if it is generic or specific to this activity
            if (eventType.classType == Application::class.java ||
                eventType.classType == DeliveryActivity::class.java
            ) {
                handleEvent(it)
            }
        })

        supportFragmentManager.addOnBackStackChangedListener(this)

        //FragmentManager automatically saves and restores fragments over configuration changes,
        // so we only need to add the fragment if the savedInstanceState is null.
        if (savedInstanceState == null) {
            initFragment()
        }else{
            setHomeAsUpEnabled()
        }

    }


    /**
     *  Handles events, it may be network error or any other event
     */
    private fun handleEvent(onEvent: OnEvent<*>) {
        when (onEvent.consumeEvent()) {
            is OnDeliveryItemSelected -> {
                openDetailFragment()
            }
        }
    }


    private fun initFragment() {
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, DeliveryListFragment.newInstance())
            .commit()
    }

    private fun openDetailFragment() {
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, DeliveryDetailFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }

    // shows back button on action bar if more than one fragment is in back stack
    private fun setHomeAsUpEnabled(){
        val upEnabled = supportFragmentManager.backStackEntryCount > 0
        supportActionBar?.setDisplayHomeAsUpEnabled(upEnabled)
    }

    override fun onBackStackChanged() {
        setHomeAsUpEnabled()
    }

    override fun onSupportNavigateUp(): Boolean {
        supportFragmentManager.popBackStack()
        return true
    }

}
