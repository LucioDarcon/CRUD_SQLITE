package com.example.controlsales.recyclerviews

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.controlsales.R
import com.example.controlsales.databinding.RecyclerItemPurchaseBinding
import com.example.controlsales.dto.PurchaseDto
import com.example.controlsales.holders.ViewHolderRecyclerItemPurchase

class AdapterPurchase : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var mItems: List<PurchaseDto> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val mCardCustomerComponentBinding = DataBindingUtil.inflate<RecyclerItemPurchaseBinding>(
            LayoutInflater.from(parent.context),
            R.layout.recycler_item_purchase,
            null,
            false
        )
        return ViewHolderRecyclerItemPurchase(mCardCustomerComponentBinding, parent.context)
    }

    fun submitList(listPurchaseDto: List<PurchaseDto>) {
        mItems = listPurchaseDto
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ViewHolderRecyclerItemPurchase -> {
                holder.bind(mItems[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return mItems.size
    }


}