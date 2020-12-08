package com.example.controlsales.holders

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.example.controlsales.databinding.RecyclerItemPurchaseBinding
import com.example.controlsales.dto.PurchaseDto
import com.example.controlsales.entities.Customer

class ViewHolderRecyclerItemPurchase constructor(recyclerItemPurchaseBinding: RecyclerItemPurchaseBinding, context: Context) : RecyclerView.ViewHolder(recyclerItemPurchaseBinding.root) {

    private val mBinding = recyclerItemPurchaseBinding

    fun bind(purchaseDto: PurchaseDto) {
        mBinding.purchase = purchaseDto
    }

}