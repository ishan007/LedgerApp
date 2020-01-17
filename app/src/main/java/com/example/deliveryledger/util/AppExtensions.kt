package com.example.deliveryledger.util

import android.net.Uri
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.deliveryledger.R


@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url:String?) {
    Glide.with(view.context)
        .setDefaultRequestOptions(RequestOptions
            .placeholderOf(R.mipmap.ic_launcher)
            .error(R.mipmap.ic_launcher))
        .load(Uri.parse(url ?: ""))
        .into(view)
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

