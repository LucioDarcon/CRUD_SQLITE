package com.example.controlsales.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.controlsales.R
import com.example.controlsales.business.CustomerBusiness
import com.example.controlsales.entities.Customer
import com.example.controlsales.recyclerviews.RecyclerViewCustomer

class SearchCustomerFragment : Fragment() {

    private lateinit var mCustomerBusiness: CustomerBusiness

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewMain = inflater.inflate(R.layout.search_customer_fragment, container, false)
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
        viewMain.refreshDrawableState()
        return viewMain
    }

    private fun getArrayListCustomer(itemView: View): ArrayList<Customer>{
        var mArrayListCustomer = ArrayList<Customer>()
        try {
            mCustomerBusiness = CustomerBusiness(itemView.context)
            mArrayListCustomer = mCustomerBusiness.getAllCustomer()
        }catch (e: Exception){
            throw e
        }
        return mArrayListCustomer
    }
}
