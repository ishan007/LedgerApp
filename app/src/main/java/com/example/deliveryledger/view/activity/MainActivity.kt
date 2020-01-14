package com.example.deliveryledger.view.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.deliveryledger.R

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun login(view: View) {
        startActivity(Intent(view.context, DeliveryActivity::class.java))
        finish()
    }

}
