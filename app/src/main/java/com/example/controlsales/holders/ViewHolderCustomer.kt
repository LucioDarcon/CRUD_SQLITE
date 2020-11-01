package com.example.controlsales.holders

import android.media.Image
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.controlsales.business.CustomerBusiness
import com.example.controlsales.entities.Customer
import com.example.controlsales.recyclerviews.AdapterCustomer
import kotlinx.android.synthetic.main.recycler_customer.view.*

class ViewHolderCustomer constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var nameCustomer: TextView = itemView.itemCustomerName
    private var emailCustomer: TextView = itemView.itemCustomerEmail
    private var telephoneCustomer: TextView = itemView.itemCustomerTelephone
    private var cpfCustomer: TextView = itemView.itemCustomerCpf
    private var idCustomer: TextView = itemView.itemCustomerId
    private var imgEditCustomer : ImageView = itemView.imgEditCustomer
    private var mDeleteCustomer : ImageView     = itemView.imgDeleteCustomer

    fun bind(customer: Customer, mOnCLickCustomer: AdapterCustomer.OnClickCustomer) {
        nameCustomer.text = customer.name
        emailCustomer.text = customer.email
        telephoneCustomer.text = customer.telephone
        cpfCustomer.text = customer.cpf
        idCustomer.text = customer.id.toString()

        imgEditCustomer.setOnClickListener {
            mOnCLickCustomer.onClickEditCustomer(customer)
        }

        mDeleteCustomer.setOnClickListener {
            mOnCLickCustomer.onClickDeleteCustomer(customer)
        }

    }

}