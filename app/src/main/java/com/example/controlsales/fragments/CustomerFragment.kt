package com.example.controlsales.fragments

import android.app.Activity
import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.controlsales.R
import com.example.controlsales.business.CustomerBusiness
import com.example.controlsales.databinding.CardCustomerComponentBinding
import com.example.controlsales.databinding.CustomerFragmentBinding
import com.example.controlsales.dialogs.DialogViewCustomerPhoto
import com.example.controlsales.dialogs.OnEditCustomer
import com.example.controlsales.dialogs.RegisterCustomerDialog
import com.example.controlsales.entities.Customer
import com.example.controlsales.recyclerviews.AdapterCustomer

class CustomerFragment : Fragment(), AdapterCustomer.OnClickCustomer, OnEditCustomer.View, View.OnClickListener {

    private lateinit var mCustomerBusiness: CustomerBusiness
    private var mAdapterCustomer = AdapterCustomer()
    private lateinit var mView: CustomerFragmentBinding
    private val resultLoadImage = 1
    private lateinit var mRegisterCustomerDialog: RegisterCustomerDialog
    private var mConfirmDeleteCustomer = Customer()
    private lateinit var mCardCustomerComponentBinding: CardCustomerComponentBinding

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
            if (mView.floatButtonAddCustomer.drawable.constantState == ContextCompat.getDrawable(
                    context!!,
                    R.drawable.ic_delete_black_24dp
                )?.constantState
            ) {
                confirmDeleteCustomer(mConfirmDeleteCustomer)
            } else {
                RegisterCustomerDialog(context!!, Customer(), this).show()
            }
        }

        mCustomerBusiness = CustomerBusiness(context!!)

        initRecyclerView()

        setListeners()

        return mView.root
    }

    private fun setListeners() {
        mView.recyclerViewCustomer.setOnClickListener(this)
    }

    private fun initRecyclerView() {
        mView.recyclerViewCustomer.layoutManager = LinearLayoutManager(context)
        mView.recyclerViewCustomer.adapter = mAdapterCustomer
        mAdapterCustomer.submitList(mCustomerBusiness.getAllCustomer(), this)
    }

    override fun onClickEditCustomer(customer: Customer) {
        RegisterCustomerDialog(context!!, customer, this).show()
    }

    override fun onClickDeleteCustomer(
        customer: Customer,
        cardCustomerComponentBinding: CardCustomerComponentBinding
    ) {
        mConfirmDeleteCustomer        = customer
        mCardCustomerComponentBinding = cardCustomerComponentBinding
        changeStatsToCardDelete()
    }

    override fun onClickImageViewCustomer(customer: Customer) {
        DialogViewCustomerPhoto(context!!, customer).show()
    }

    override fun onCLickCardCustomer(customer: Customer) {
        val args = Bundle()
        args.putInt("customerId", customer.id)

        val generalCustomerFragment           = PurchaseFragment()
        generalCustomerFragment.arguments     = args
        val spFragment : FragmentTransaction? = activity?.supportFragmentManager?.beginTransaction()
        spFragment?.replace(
            R.id.content_fragment,
            generalCustomerFragment
        )

        spFragment?.addToBackStack("customerFragment")?.commit()
    }

    private fun changeStatsToCardDelete() {
        mCardCustomerComponentBinding.recyclerViewAllCustomer.setBackgroundColor(
            ContextCompat.getColor(
                context!!,
                R.color.secondaryGrey
            )
        )
        mView.floatButtonAddCustomer.setImageDrawable(
            ContextCompat.getDrawable(
                context!!,
                R.drawable.ic_delete_black_24dp
            )
        )
        mCardCustomerComponentBinding.cardCustomerComponentCustomerCancelClickImageView.visibility = View.VISIBLE
        mCardCustomerComponentBinding.cardCustomerComponentCustomerCancelClickImageView.setOnClickListener {
            resetStatCard()
        }
    }

    private fun confirmDeleteCustomer(customer: Customer) {
        mCustomerBusiness.deleteCustomer(customer.id.toString())
        mAdapterCustomer.submitList(mCustomerBusiness.getAllCustomer(), this)
        mAdapterCustomer.notifyDataSetChanged()
        resetStatCard()
    }

    private fun resetStatCard() {
        mView.floatButtonAddCustomer.setImageDrawable(
            ContextCompat.getDrawable(
                context!!,
                R.drawable.ic_add_white_24dp
            )
        )
        mCardCustomerComponentBinding.recyclerViewAllCustomer.setBackgroundColor(
            ContextCompat.getColor(
                context!!,
                R.color.grey
            )
        )
        mCardCustomerComponentBinding.cardCustomerComponentCustomerCancelClickImageView.visibility = View.GONE
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
        val filePathColumn =
            arrayOf(MediaStore.Images.Media.DATA)
        val cursor: Cursor? =
            context?.contentResolver?.query(selectedImage!!, filePathColumn, null, null, null)
        cursor?.moveToFirst()
        val columnIndex: Int = cursor!!.getColumnIndex(filePathColumn[0])
        val picturePath: String = cursor.getString(columnIndex)
        mRegisterCustomerDialog.showImage(selectedImage!!, picturePath)
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

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.recyclerViewCustomer -> {
                resetStatCard()
            }
        }
    }


}
