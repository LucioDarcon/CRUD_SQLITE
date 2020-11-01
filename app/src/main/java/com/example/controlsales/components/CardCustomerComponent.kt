package com.example.controlsales.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.databinding.DataBindingUtil
import com.example.controlsales.R
import com.example.controlsales.databinding.CardCustomerComponentBinding
import com.example.controlsales.dto.CustomerDto

class CardCustomerComponent (
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle) {

    private val mBinding: CardCustomerComponentBinding = DataBindingUtil.inflate(
        LayoutInflater.from(context), R.layout.card_customer_component, this, true
    )

    fun setCardCustomer(customerDto: CustomerDto) {
        mBinding.customerDto = customerDto
        mBinding.executePendingBindings()
    }

}