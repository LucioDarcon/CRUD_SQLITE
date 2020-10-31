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
import com.example.controlsales.dialogs.RegisterCustomerDialog
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
        val viewMain = inflater.inflate(R.layout.search_customer_fragment, container, false)
        val recyclerViewMain = createRecyclerViewCustomer(viewMain)

        val floatButton =
            viewMain.findViewById<FloatingActionButton>(R.id.floatButtonAddCustomer)
        floatButton.setOnClickListener {
            try {
               ViewHolderCustomer(viewMain).showDialogRegisterCustomer(viewMain, recyclerViewMain)
            }catch (e: IllegalStateException){
                showDialogRegisterCustomer(viewMain, recyclerViewMain)
            }
        }

        return viewMain
    }

    private fun showDialogRegisterCustomer(viewMain: View,recyclerViewCustomer: RecyclerViewCustomer){
        mCustomerBusiness = CustomerBusiness(viewMain.context)
        val mRegisterCustomerDialog = RegisterCustomerDialog(viewMain.context)
        mRegisterCustomerDialog.show()
    }

    private fun createRecyclerViewCustomer(viewMain: View): RecyclerViewCustomer {
        var recyclerViewCustomer = RecyclerViewCustomer()
        val recyclerView = viewMain.findViewById<RecyclerView>(R.id.recyclerViewCustomer)
        try {
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
        return recyclerViewCustomer
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
