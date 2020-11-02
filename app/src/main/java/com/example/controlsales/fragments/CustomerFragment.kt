package com.example.controlsales.fragments

import android.app.Activity
import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.controlsales.R
import com.example.controlsales.business.CustomerBusiness
import com.example.controlsales.databinding.CustomerFragmentBinding
import com.example.controlsales.dialogs.OnEditCustomer
import com.example.controlsales.dialogs.RegisterCustomerDialog
import com.example.controlsales.entities.Customer
import com.example.controlsales.recyclerviews.AdapterCustomer
import kotlinx.android.synthetic.main.customer_fragment.view.*

class CustomerFragment : Fragment(), AdapterCustomer.OnClickCustomer, OnEditCustomer.View {

    private lateinit var mCustomerBusiness: CustomerBusiness
    private var mAdapterCustomer = AdapterCustomer()
    private lateinit var mView: CustomerFragmentBinding
    private val resultLoadImage = 1
    private lateinit var mRegisterCustomerDialog: RegisterCustomerDialog

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mView = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.customer_fragment,
            null,
            false
        )

        mView.floatButtonAddCustomer.setOnClickListener {
            RegisterCustomerDialog(context!!, Customer(), this).show()
        }

        mCustomerBusiness = CustomerBusiness(context!!)

        initRecyclerView()

        return mView.root
    }

    private fun initRecyclerView() {
        mView.recyclerViewCustomer.layoutManager = LinearLayoutManager(context)
        mView.recyclerViewCustomer.adapter = mAdapterCustomer
        mAdapterCustomer.submitList(mCustomerBusiness.getAllCustomer(), this)
    }

    override fun onClickEditCustomer(customer: Customer) {
        RegisterCustomerDialog(context!!, customer, this).show()
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == Activity.RESULT_OK && data != null) {
            showImage(data)
        }
    }

    private fun showImage(data: Intent?) {
        val selectedImage = data?.data
        mRegisterCustomerDialog.showImage(selectedImage!!)
    }

    override fun getImage() {
        val i = Intent(
            Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        )
        startActivityForResult(i, resultLoadImage)
    }

    override fun getInstanceDialog(mDialog: RegisterCustomerDialog) {
        mRegisterCustomerDialog = mDialog
    }


}
