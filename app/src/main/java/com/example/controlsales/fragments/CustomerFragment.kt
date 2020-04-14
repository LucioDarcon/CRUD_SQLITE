package com.example.controlsales.fragments

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.controlsales.R
import com.example.controlsales.business.CustomerBusiness
import com.example.controlsales.entities.Customer
import com.example.controlsales.holders.ViewHolderCustomer
import com.example.controlsales.recyclerviews.RecyclerViewCustomer
import com.google.android.material.floatingactionbutton.FloatingActionButton

class CustomerFragment : Fragment() {

    private lateinit var mCustomerBusiness: CustomerBusiness

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        var viewMain = inflater.inflate(R.layout.search_customer_fragment, container, false)
        viewMain = createRecyclerViewCustomer(viewMain)

        val floatButton = viewMain.findViewById<FloatingActionButton>(R.id.floatButtonAddCustomer)
        floatButton.setOnClickListener {
            showDialogRegisterCustomer(viewMain)
        }

        return viewMain
    }

    private fun showDialogRegisterCustomer(viewMain: View) {
        val fromBottom = AnimationUtils.loadAnimation(viewMain.context, R.anim.from_bottom)
        val mDialog = Dialog(viewMain.context)
        mDialog.setContentView(R.layout.dialog_customer)
        mDialog.window?.setBackgroundDrawableResource(android.R.color.transparent);
        mDialog.show()
        val btnSalveCustomer = mDialog.findViewById<Button>(R.id.btnSaveCustomer)
        btnSalveCustomer.animation = fromBottom
        btnSalveCustomer.setOnClickListener {
            try {
                val edtNameCustomer = mDialog.findViewById<EditText>(R.id.edtNameCustomer)
                val edtEmailCustomer = mDialog.findViewById<EditText>(R.id.edtEmailCustomer)
                val edtTelephoneCustomer = mDialog.findViewById<EditText>(R.id.edtTelephoneCustomer)
                val edtCPFCustomer = mDialog.findViewById<EditText>(R.id.edtCpfCustomer)
                val mCustomer = Customer(
                    name = edtNameCustomer.text.toString(),
                    email = edtEmailCustomer.text.toString(),
                    telephone = edtTelephoneCustomer.text.toString(),
                    cpf = edtCPFCustomer.text.toString()
                )
                val result = mCustomerBusiness.insertCustomer(mCustomer)
                if (result > 0) {
                    Toast.makeText(
                        viewMain.context,
                        resources.getString(R.string.salvo_com_sucesso),
                        Toast.LENGTH_LONG
                    ).show()
                } else Toast.makeText(
                    viewMain.context,
                    resources.getString(R.string.erro_inesperado),
                    Toast.LENGTH_LONG
                ).show()
            } catch (e: Exception) {
                Toast.makeText(
                    viewMain.context,
                    resources.getString(R.string.erro_inesperado),
                    Toast.LENGTH_LONG
                ).show()
            } finally {
                mDialog.dismiss()
            }
        }
        val btnCloseDialog = mDialog.findViewById<Button>(R.id.btnCloseDialogCustomer)
        btnCloseDialog.animation = fromBottom
        btnCloseDialog.setOnClickListener {
            mDialog.dismiss()
        }
    }

    private fun createRecyclerViewCustomer(viewMain: View): View {
        try {
            val recyclerViewCustomer: RecyclerViewCustomer
            val recyclerView = viewMain.findViewById<RecyclerView>(R.id.recyclerViewCustomer)
            recyclerView.apply {
                layoutManager = LinearLayoutManager(context)
                recyclerViewCustomer = RecyclerViewCustomer()
                adapter = recyclerViewCustomer
            }
            recyclerViewCustomer.submitList(
                getArrayListCustomer(viewMain)
            )
        } catch (e: Exception) {
            Toast.makeText(
                viewMain.context,
                resources.getString(R.string.erro_inesperado),
                Toast.LENGTH_LONG
            ).show()
        }
        return viewMain
    }


    private fun getArrayListCustomer(itemView: View): ArrayList<Customer> {
        var mArrayListCustomer = ArrayList<Customer>()
        try {
            mCustomerBusiness = CustomerBusiness(itemView.context)
            mArrayListCustomer = mCustomerBusiness.getAllCustomer()
        } catch (e: Exception) {
            throw e
        }
        return mArrayListCustomer
    }
}
