package com.example.deliveryledger.util

import android.net.Uri
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.deliveryledger.R
import java.math.BigDecimal


@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url:String?) {
    Glide.with(view.context)
        .setDefaultRequestOptions(RequestOptions
            .placeholderOf(R.drawable.default_image_thumbnail)
            .error(R.drawable.default_image_thumbnail))
        .load(Uri.parse(url ?: ""))
        .into(view)
}

@BindingAdapter("deliveryFee", "surcharge")
fun setCost(view: TextView, deliveryFeeStr: String?, surchargeStr: String?){
    val deliveryFee = convertStringToBigDecimal(deliveryFeeStr)
    val surcharge = convertStringToBigDecimal(surchargeStr)
    view.text = String.format("$%.2f", deliveryFee+surcharge)
}


fun convertStringToBigDecimal(str: String?): BigDecimal {
    return if (str?.contains("$") == true)
        str.replace("$", "").toBigDecimal() else 0.0.toBigDecimal()

}

