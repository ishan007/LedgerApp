package com.example.deliveryledger.util

import android.net.Uri
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.RequestManager


@BindingAdapter("imageUrl", "glideInstance")
fun loadImage(view: ImageView, url:String?, requestManager: RequestManager?) {
  requestManager?.load(Uri.parse(url ?: ""))?.into(view)
}

@BindingAdapter("deliveryFee", "surcharge")
fun setCost(view: TextView, deliveryFeeStr: String?, surchargeStr: String?){
    val deliveryFee = convertStringToDouble(deliveryFeeStr)
    val surcharge = convertStringToDouble(surchargeStr)
    view.text = String.format("$%.2f", deliveryFee+surcharge)
}

fun convertStringToDouble(str: String?): Double {
    return if (str?.contains("$") == true)
        str.replace("$", "").toDouble() else 0.0

}

