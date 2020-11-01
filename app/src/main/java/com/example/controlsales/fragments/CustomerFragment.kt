package com.example.controlsales.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.controlsales.R
import com.example.controlsales.business.CustomerBusiness
import com.example.controlsales.dialogs.OnEditCustomer
import com.example.controlsales.dialogs.RegisterCustomerDialog
import com.example.controlsales.entities.Customer
import com.example.controlsales.recyclerviews.AdapterCustomer
import kotlinx.android.synthetic.main.customer_fragment.view.*

class CustomerFragment : Fragment(), AdapterCustomer.OnClickCustomer, OnEditCustomer.View {

    private lateinit var mCustomerBusiness: CustomerBusiness
    private var mAdapterCustomer = AdapterCustomer()
    private lateinit var mView: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mView = inflater.inflate(R.layout.customer_fragment, container, false)

        mView.floatButtonAddCustomer.setOnClickListener {
            RegisterCustomerDialog(mView.context, Customer(), this).show()
        }

        mCustomerBusiness = CustomerBusiness(mView.context)

        initRecyclerView()

        return mView
    }

    private fun initRecyclerView() {
        mView.recyclerViewCustomer.layoutManager = LinearLayoutManager(context)
        mView.recyclerViewCustomer.adapter       = mAdapterCustomer
        mAdapterCustomer.submitList(mCustomerBusiness.getAllCustomer(), this)
    }

    override fun onClickEditCustomer(customer: Customer) {
        RegisterCustomerDialog(mView.context, customer, this).show()
    }

    override fun onClickDeleteCustomer(customer: Customer) {
        mCustomerBusiness.deleteCustomer(customer.id.toString())
        mAdapterCustomer.submitList(mCustomerBusiness.getAllCustomer(), this)
        mAdapterCustomer.notifyDataSetChanged()
    }

    override fun updateData() {
        mAdapterCustomer.submitList(mCustomerBusiness.getAllCustomer(), this)
        mAdapterCustomer.notifyDataSetChanged()
    }


}
