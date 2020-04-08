package com.example.controlsales.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.controlsales.R
import com.example.controlsales.business.CustomerBusiness
import com.example.controlsales.entities.Customer

class AddCustomerFragment : Fragment() {

    private lateinit var mCustomerBusiness: CustomerBusiness

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val mView = inflater.inflate(R.layout.add_customer_fragment, container, false)
        val btnAddCustomer = mView.findViewById<Button>(R.id.btnSaveCustomer)

        btnAddCustomer.setOnClickListener {
            try {
                if (submitFormAddCustomer(mView)) {
                    Toast.makeText(
                        context,
                        resources.getString(R.string.salvo_com_sucesso),
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    Toast.makeText(
                        context,
                        resources.getString(R.string.erro_ao_salvar),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }catch (e: Exception){
                Toast.makeText(
                    context,
                    resources.getString(R.string.erro_inesperado),
                    Toast.LENGTH_LONG
                ).show()
            }finally {
                clearText(mView)
            }
        }

        return mView
    }

    private fun submitFormAddCustomer(mView: View): Boolean {
        mCustomerBusiness = CustomerBusiness(mView.context)
        var ret = false
        val mEdtNameCustomer = mView.findViewById<EditText>(R.id.edtNameCustomer)
        val mEdtEmailCustomer = mView.findViewById<EditText>(R.id.edtEmailCustomer)
        val mEdtTelephoneCustomer = mView.findViewById<EditText>(R.id.edtTelephoneCustomer)
        val mEdtCpfCustomer = mView.findViewById<EditText>(R.id.edtCpfCustomer)

        val mCustomer = Customer(
            mEdtNameCustomer.text.toString(),
            mEdtEmailCustomer.text.toString(),
            mEdtTelephoneCustomer.text.toString(),
            mEdtCpfCustomer.text.toString()
        )
        if(mCustomerBusiness.insertCustomer(mCustomer) > 0) ret = true
        return ret
    }

    private fun clearText(mView: View){
        val mEdtNameCustomer = mView.findViewById<EditText>(R.id.edtNameCustomer)
        val mEdtEmailCustomer = mView.findViewById<EditText>(R.id.edtEmailCustomer)
        val mEdtTelephoneCustomer = mView.findViewById<EditText>(R.id.edtTelephoneCustomer)
        val mEdtCpfCustomer = mView.findViewById<EditText>(R.id.edtCpfCustomer)

        mEdtNameCustomer.setText("")
        mEdtEmailCustomer.setText("")
        mEdtTelephoneCustomer.setText("")
        mEdtCpfCustomer.setText("")

        mEdtNameCustomer.clearFocus()
        mEdtEmailCustomer.clearFocus()
        mEdtTelephoneCustomer.clearFocus()
        mEdtCpfCustomer.clearFocus()
    }
}
