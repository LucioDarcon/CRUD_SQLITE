package com.example.controlsales.holders

import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.controlsales.entities.Customer
import kotlinx.android.synthetic.main.recycler_customer.view.*

class ViewHolderCustomer constructor(itemView: View): RecyclerView.ViewHolder(itemView) {

    private val nameCustomer : TextView = itemView.itemCustomerName
    private val emailCustomer : TextView = itemView.itemCustomerEmail
    private val telephoneCustomer : TextView = itemView.itemCustomerTelephone
    private val cpfCustomer : TextView = itemView.itemCustomerCpf


    fun bind(customer: Customer){
        nameCustomer.text = customer.name
        emailCustomer.text = customer.email
        telephoneCustomer.text = customer.telephone
        cpfCustomer.text = customer.cpf

        itemView.setOnClickListener(View.OnClickListener {
            Toast.makeText(itemView.context, nameCustomer.text, Toast.LENGTH_LONG).show()
        })
    }


}