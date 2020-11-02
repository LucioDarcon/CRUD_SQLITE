package com.example.controlsales.holders

import android.widget.FrameLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.controlsales.converters.ConverterCustomer.converterCustomerToCustomerDto
import com.example.controlsales.databinding.CardCustomerComponentBinding
import com.example.controlsales.entities.Customer
import com.example.controlsales.recyclerviews.AdapterCustomer

class ViewHolderCustomer constructor(cardCustomerComponentBinding: CardCustomerComponentBinding) : RecyclerView.ViewHolder(cardCustomerComponentBinding.root) {

    private var mCardCustomerComponentBinding = cardCustomerComponentBinding

    fun bind(customer: Customer, mOnCLickCustomer: AdapterCustomer.OnClickCustomer) {

        mCardCustomerComponentBinding.cardCustomerComponentDeleteCustomerImageView.setOnClickListener {
            mOnCLickCustomer.onClickEditCustomer(customer)
        }

        mCardCustomerComponentBinding.cardCustomerComponentGeneralCardView.setOnLongClickListener {
            mOnCLickCustomer.onClickDeleteCustomer(customer)
            true
        }

        mCardCustomerComponentBinding.customerDto = converterCustomerToCustomerDto(customer)

    }

}