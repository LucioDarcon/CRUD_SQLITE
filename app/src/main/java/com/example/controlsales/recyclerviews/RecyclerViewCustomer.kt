package com.example.controlsales.recyclerviews

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.controlsales.R
import com.example.controlsales.entities.Customer
import com.example.controlsales.holders.ViewHolderCustomer

class RecyclerViewCustomer : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: List<Customer> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolderCustomer(LayoutInflater.from(parent.context).inflate(R.layout.recycler_customer, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is ViewHolderCustomer -> {
                holder.bind(items[position])
            }
        }
    }

    fun submitList(listCustomer: List<Customer>){
        this.items = listCustomer
    }
}