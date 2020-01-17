package com.example.deliveryledger

import org.junit.Before
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.MockitoAnnotations


@RunWith(JUnit4::class)
/*@RunWith(
    RobolectricTestRunner::class)
    @Config(application=DeliveryApplication::class, sdk = [Build.VERSION_CODES.P])*/
abstract class BaseUnitTest{

    @Before
    open fun setup(){
        MockitoAnnotations.initMocks(this)
    }

}