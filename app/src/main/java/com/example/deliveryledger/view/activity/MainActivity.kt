package com.example.deliveryledger.view.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.deliveryledger.R
import com.example.deliveryledger.di.application.DeliveryApplication

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as DeliveryApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun login(view: View) {
        startActivity(Intent(view.context, DeliveryActivity::class.java))
        finish()
    }

}
