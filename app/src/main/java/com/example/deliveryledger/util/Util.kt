package com.example.deliveryledger.util

import android.util.Log

/**
 * Util class
 */
class Util {
    companion object{

        fun logError(msg: String){
            Log.e(AppConstants.TAG, msg)
        }

        fun logDebug(msg: String){
            Log.d(AppConstants.TAG, msg)
        }

    }
}