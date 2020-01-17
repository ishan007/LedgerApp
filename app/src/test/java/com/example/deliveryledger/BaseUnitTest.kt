package com.example.deliveryledger

import android.os.Build
import com.example.deliveryledger.di.DeliveryApplication
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config


//@RunWith(JUnit4::class)
@RunWith(
    RobolectricTestRunner::class)
    @Config(application= DeliveryApplication::class, sdk = [Build.VERSION_CODES.P])
abstract class BaseUnitTest{

    @Before
    open fun setup(){
        MockitoAnnotations.initMocks(this)
    }

}