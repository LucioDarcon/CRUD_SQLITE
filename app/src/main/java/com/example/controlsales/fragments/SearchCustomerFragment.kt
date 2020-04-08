package com.example.controlsales.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.controlsales.R
import com.example.controlsales.entities.Customer
import com.example.controlsales.recyclerviews.RecyclerViewCustomer

class SearchCustomerFragment : Fragment() {

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
            listOf(
                Customer(
                    "Lucio",
                    "luciodarcon1997@hotmail.com",
                    "(82) 98844-7661", "999.999.999-99"
                ), Customer("Levi", "Levi1997@hotmail.com", "(82) 98844-7661", "888.888.888-88")
            )
        )
        viewMain.refreshDrawableState()
        return viewMain
    }
}