package com.example.deliveryledger.view.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.example.deliveryledger.R
import com.example.deliveryledger.di.application.DeliveryApplication

/**
 *  Main launching activity used as splash screen
 */
class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as DeliveryApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        startDeliveryActivity()
    }


    // starting delivery activity with delay of 1 second to give feel of splash screen
    private fun startDeliveryActivity(){
        Handler().postDelayed({
            val intent = Intent(this, DeliveryActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
        }, 1000)
    }

}
