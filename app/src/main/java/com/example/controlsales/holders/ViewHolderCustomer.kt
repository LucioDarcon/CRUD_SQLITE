package com.example.controlsales.holders

import androidx.recyclerview.widget.RecyclerView
import com.example.controlsales.converters.ConverterCustomer.converterCustomerToCustomerDto
import com.example.controlsales.databinding.CardCustomerComponentBinding
import com.example.controlsales.entities.Customer
import com.example.controlsales.recyclerviews.AdapterCustomer

class ViewHolderCustomer constructor(cardCustomerComponentBinding: CardCustomerComponentBinding) : RecyclerView.ViewHolder(cardCustomerComponentBinding.root) {

    private var mCardCustomerComponentBinding = cardCustomerComponentBinding

    fun bind(customer: Customer, mOnCLickCustomer: AdapterCustomer.OnClickCustomer) {

        mCardCustomerComponentBinding.imgEditCustomer.setOnClickListener {
            mOnCLickCustomer.onClickEditCustomer(customer)
        }

        mCardCustomerComponentBinding.imgDeleteCustomer.setOnClickListener {
            mOnCLickCustomer.onClickDeleteCustomer(customer)
        }

        mCardCustomerComponentBinding.customerDto = converterCustomerToCustomerDto(customer)

    }

}