package com.example.controlsales.dialogs

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.FrameLayout
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.controlsales.R
import com.example.controlsales.business.CustomerBusiness
import com.example.controlsales.databinding.DialogCustomerBinding
import com.example.controlsales.entities.Customer
import kotlinx.android.synthetic.main.dialog_customer.*

class RegisterCustomerDialog(
    context: Context,
    private val customer: Customer,
    private val onEditCustomer: OnEditCustomer.View
) : Dialog(context),
    View.OnClickListener {

    private lateinit var mCustomerBusiness: CustomerBusiness
    private var mCustomer: Customer = customer
    private lateinit var mBinding: DialogCustomerBinding
    private var mView = onEditCustomer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.dialog_customer,
            null,
            false
        )
        mCustomerBusiness = CustomerBusiness(context)

        setContentView(mBinding.root)
        setListeners()
        configureDialog()
        configureWindow()
        setEntityCustomToTextFieldDialog(mCustomer)
    }

    private fun setEntityCustomToTextFieldDialog(customer: Customer) {
        if (customer.id != 0) {
            mBinding.customerDto = customer
            mBinding.executePendingBindings()
        }
    }

    private fun configureWindow() {
        window?.setBackgroundDrawableResource(android.R.color.transparent)
        window?.setLayout(
            FrameLayout.LayoutParams.MATCH_PARENT,
            FrameLayout.LayoutParams.MATCH_PARENT
        )
    }

    private fun configureDialog() {
        btnSaveCustomer.animation = AnimationUtils.loadAnimation(context, R.anim.from_bottom)
    }

    private fun validationFields(): Boolean {
        return edtNameCustomer.text.toString() != ""
                && edtCpfCustomer.text.toString() != ""
                && edtTelephoneCustomer.text.toString() != ""
                && edtEmailCustomer.text.toString() != ""
    }

    private fun setListeners() {
        btnSaveCustomer.setOnClickListener(this)
        btnCloseDialogCustomer.setOnClickListener(this)
    }

    private fun saveCustomer() {
        if (validationFields()) {

            var mCustomer = Customer()
            if (mBinding.customerDto != null) {
                mCustomer = Customer(
                    mBinding.customerDto!!.id,
                    name = edtNameCustomer.text.toString(),
                    email = edtEmailCustomer.text.toString(),
                    telephone = edtTelephoneCustomer.text.toString(),
                    cpf = edtCpfCustomer.text.toString(),
                    idAdm = mBinding.customerDto!!.idAdm
                )
            } else {
                mCustomer = Customer(
                    name = edtNameCustomer.text.toString(),
                    email = edtEmailCustomer.text.toString(),
                    telephone = edtTelephoneCustomer.text.toString(),
                    cpf = edtCpfCustomer.text.toString()
                )
            }

            val result = mCustomerBusiness.insertCustomer(mCustomer)

            if (result > 0) Toast.makeText(context, R.string.salvo_com_sucesso, Toast.LENGTH_LONG).show()
            else Toast.makeText(context, R.string.erro_ao_salvar, Toast.LENGTH_LONG).show()
            mView.updateData()
            dismiss()
        }
    }

    private fun closeDialog() {
        btnCloseDialogCustomer.setOnClickListener {
            dismiss()
        }
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btnCloseDialogCustomer -> {
                closeDialog()
            }
            R.id.btnSaveCustomer -> {
                saveCustomer()
            }
        }
    }


}