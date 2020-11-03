package com.example.controlsales.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import com.example.controlsales.R
import com.example.controlsales.databinding.CardCustomerComponentBinding
import com.example.controlsales.databinding.GeneralCardCustomerBinding
import com.example.controlsales.databinding.GeneralCustomerFragmentBinding
import com.example.controlsales.dto.CustomerDto

class CardGeneralCustomerComponent (
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : LinearLayout(context, attrs, defStyle) {

     private val mBinding: GeneralCardCustomerBinding = DataBindingUtil.inflate(
        LayoutInflater.from(context), R.layout.general_card_customer, this, true
    )

    fun setCardGeneralCustomer(customerDto: CustomerDto) {
        mBinding.customerDto = customerDto
        mBinding.executePendingBindings()
    }

}