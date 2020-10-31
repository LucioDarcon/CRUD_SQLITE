package com.example.controlsales.dialogs

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.Toast
import com.example.controlsales.R
import com.example.controlsales.business.CustomerBusiness
import com.example.controlsales.entities.Customer
import kotlinx.android.synthetic.main.dialog_customer.*

class RegisterCustomerDialog(context: Context) : Dialog(context) {

    private lateinit var mCustomerBusiness: CustomerBusiness

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.dialog_customer)
        mCustomerBusiness = CustomerBusiness(context)
        configureWindow()
        configureDialog()
    }

    private fun configureWindow() {
        window?.setBackgroundDrawableResource(android.R.color.transparent)
        window?.setLayout(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT)
    }

    private fun configureDialog() {
        val fromBottom = AnimationUtils.loadAnimation(context, R.anim.from_bottom)
        btnSaveCustomer.animation = fromBottom
        btnSaveCustomer.setOnClickListener {
            try {
                val mCustomer = Customer(
                    name = edtNameCustomer.text.toString(),
                    email = edtEmailCustomer.text.toString(),
                    telephone = edtTelephoneCustomer.text.toString(),
                    cpf = edtCpfCustomer.text.toString()
                )
                val result = mCustomerBusiness.insertCustomer(mCustomer)
                if (result > 0) {
                    Toast.makeText(context, R.string.salvo_com_sucesso, Toast.LENGTH_LONG).show()
                } else{
                    Toast.makeText(context, R.string.erro_ao_salvar, Toast.LENGTH_LONG).show()
                }
            } catch (e: Exception) {
                Toast.makeText(context, R.string.erro_inesperado, Toast.LENGTH_LONG).show()
            } finally {
                dismiss()
            }
        }
    }


}