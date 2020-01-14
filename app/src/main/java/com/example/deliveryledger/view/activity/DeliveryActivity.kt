package com.example.deliveryledger.view.activity

import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.deliveryledger.R
import com.example.deliveryledger.databinding.ActivityDeliveryBinding
import com.example.deliveryledger.view.fragment.DeliveryDetailFragment
import com.example.deliveryledger.view.fragment.MyDeliveriesFragment
import com.example.deliveryledger.viewmodel.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class DeliveryActivity : BaseActivity() {

    @Inject
    lateinit var disposable: CompositeDisposable

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var onEventObserver: PublishSubject<OnEvent<*>>

    private lateinit var deliveryLedgerViewModel: DeliveryLedgerViewModel

    private lateinit var binding: ActivityDeliveryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_delivery)
        initFragment()

        deliveryLedgerViewModel = ViewModelProviders.of(this, viewModelFactory).
            get(DeliveryLedgerViewModel::class.java)


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

    override fun onStart() {
        super.onStart()
        onEventObserver.observeOn(AndroidSchedulers.mainThread()).subscribe(object :
            DeliveryObserver<OnEvent<*>>(onEventObserver, disposable){
            override fun onNext(t: OnEvent<*>) {
                handleEvent(t)
            }
        })
    }

    override fun onStop() {
        disposable.clear()
        super.onStop()
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
