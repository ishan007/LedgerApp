package com.example.deliveryledger.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.deliveryledger.R
import com.example.deliveryledger.databinding.DeliveryListItemBinding
import com.example.deliveryledger.repository.entities.Delivery

class DeliveryListAdapter(private val deliverySelectionListener: DeliverySelectionListener)
    : PagedListAdapter<Delivery, DeliveryListAdapter.DeliveryItemVH>(DIFF_CALLBACK){

    class DeliveryItemVH(var binding: DeliveryListItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeliveryItemVH {

        val viewDataBinding = DataBindingUtil.inflate<DeliveryListItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.delivery_list_item,
            parent, false)

        viewDataBinding.root.setOnClickListener {
            deliverySelectionListener.onDeliverySelected(viewDataBinding.delivery!! )
        }

        return DeliveryItemVH(viewDataBinding)
    }


    override fun onBindViewHolder(holder: DeliveryItemVH, position: Int) {
        holder.binding.delivery = getItem(position)
    }

    companion object {
        private val DIFF_CALLBACK = object :
            DiffUtil.ItemCallback<Delivery>() {
            // Concert details may have changed if reloaded from the database,
            // but ID is fixed.
            override fun areItemsTheSame(oldDelivery: Delivery,
                                         newConcert: Delivery) = oldDelivery.id == newConcert.id

            override fun areContentsTheSame(oldDelivery: Delivery,
                                            newDelivery: Delivery) = oldDelivery == newDelivery
        }
    }


    class DeliverySelectionListener(val onDeliverySelection: (delivery: Delivery)-> Unit){
        fun onDeliverySelected(delivery: Delivery) = onDeliverySelection(delivery)
    }

}

