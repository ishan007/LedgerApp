package com.example.deliveryledger.util

import android.util.Log

/**
 * Util class
 */
class Logger {
    companion object{

        fun e(msg: String){
            Log.e(AppConstants.TAG, msg)
        }

        fun d(msg: String){
            Log.d(AppConstants.TAG, msg)
        }

    }
}