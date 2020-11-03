package com.example.controlsales.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.controlsales.R
import com.example.controlsales.business.CustomerBusiness
import com.example.controlsales.components.CardGeneralCustomerComponent
import com.example.controlsales.converters.ConverterCustomer
import com.example.controlsales.databinding.GeneralCustomerFragmentBinding
import com.example.controlsales.entities.Customer

class GeneralCustomerFragment : Fragment() {

    private lateinit var mBinding: GeneralCustomerFragmentBinding
    private lateinit var mBusiness: CustomerBusiness

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.general_customer_fragment,
            null,
            false
        )

        val customerId = arguments?.getInt("customerId")

        mBusiness = CustomerBusiness(context!!)

//        mBinding.generalCustomerFragmentCardGeneralComponent.setCardGeneralCustomer(
//            ConverterCustomer.converterCustomerToCustomerDto(mBusiness.getCustomer(customerId.toString()))
//        )

        return mBinding.root
    }


}