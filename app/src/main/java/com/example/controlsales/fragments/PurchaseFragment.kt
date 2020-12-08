package com.example.controlsales.fragments

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.controlsales.R
import com.example.controlsales.business.CustomerBusiness
import com.example.controlsales.converters.ConverterCustomer
import com.example.controlsales.databinding.GeneralCustomerFragmentBinding
import com.example.controlsales.dto.PurchaseDto
import com.example.controlsales.entities.Customer
import com.example.controlsales.recyclerviews.AdapterPurchase
import java.io.File

class PurchaseFragment : Fragment() {

    private lateinit var mBinding: GeneralCustomerFragmentBinding
    private lateinit var mCustomerBusiness: CustomerBusiness
    private lateinit var mCurrentCustomer: Customer
    private var mAdapterPurchase = AdapterPurchase()

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

        mCustomerBusiness = CustomerBusiness(context!!)

        mCurrentCustomer = mCustomerBusiness.getCustomer(customerId.toString())

        setCurrentCustomerToLayout()
        calculateProgressBar()
        initRecyclerView()

        return mBinding.root
    }

    private fun initRecyclerView() {
        mBinding.generalCustomerFragmentRecyclerView.layoutManager = LinearLayoutManager(context)
        mBinding.generalCustomerFragmentRecyclerView.adapter = mAdapterPurchase
        mAdapterPurchase.submitList(listOf(PurchaseDto("Arroz", "Maria", 10.50, "10/10/2020"),
            PurchaseDto("Arroz", "Maria", 10.50, "10/10/2020")))
    }

    private fun calculateProgressBar() {
        mBinding.generalCustomerFragmentProgressBarFullPayment.progress = 10
    }

    private fun setCurrentCustomerToLayout() {
        mBinding.customer = mCurrentCustomer
        if (mCurrentCustomer.image.isEmpty()) {
            mBinding.generalCustomerFragmentImageViewCustomer.setImageDrawable(
                ContextCompat.getDrawable(
                    context!!,
                    R.drawable.ic_camera
                )
            )
        } else
            mBinding.generalCustomerFragmentImageViewCustomer.setImageURI(
                Uri.fromFile(
                    File(mCurrentCustomer.image)
                )
            )
    }


}