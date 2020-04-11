package com.example.controlsales.holders

import android.app.Dialog
import android.content.Intent
import android.content.SharedPreferences
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.core.app.AppLaunchChecker
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.controlsales.R
import com.example.controlsales.business.CustomerBusiness
import com.example.controlsales.entities.Customer
import com.example.controlsales.fragments.AddCustomerFragment
import com.example.controlsales.fragments.SearchCustomerFragment
import com.example.controlsales.recyclerviews.RecyclerViewCustomer
import com.example.controlsales.util.SecurityPreferences
import com.example.controlsales.view.PanelActivity
import kotlinx.android.synthetic.main.recycler_customer.view.*
import kotlinx.android.synthetic.main.search_customer_fragment.view.*

class ViewHolderCustomer constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var nameCustomer: TextView = itemView.itemCustomerName
    private var emailCustomer: TextView = itemView.itemCustomerEmail
    private var telephoneCustomer: TextView = itemView.itemCustomerTelephone
    private var cpfCustomer: TextView = itemView.itemCustomerCpf
    private var idCustomer: TextView = itemView.itemCustomerId
    private lateinit var mCustomerBusiness: CustomerBusiness


    fun bind(customer: Customer) {
        nameCustomer.text = customer.name
        emailCustomer.text = customer.email
        telephoneCustomer.text = customer.telephone
        cpfCustomer.text = customer.cpf
        idCustomer.text = customer.id.toString()

        val imgEdit = itemView.findViewById<ImageView>(R.id.imgEditCustomer)
        imgEdit.setOnClickListener(View.OnClickListener {
            showDialogAlterCustomer()
        })

    }

    private fun showDialogAlterCustomer() {
        val mDialog = Dialog(itemView.context)
        mDialog.setContentView(R.layout.dialog_alter_customer)

        val edtNameCustomer = mDialog.findViewById<EditText>(R.id.edtNameCustomer)
        edtNameCustomer.setText(nameCustomer.text)

        val edtEmailCustomer = mDialog.findViewById<EditText>(R.id.edtEmailCustomer)
        edtEmailCustomer.setText(emailCustomer.text)

        val edtTelephoneCustomer = mDialog.findViewById<EditText>(R.id.edtTelephoneCustomer)
        edtTelephoneCustomer.setText(telephoneCustomer.text)

        val edtCPFCustomer = mDialog.findViewById<EditText>(R.id.edtCpfCustomer)
        edtCPFCustomer.setText(cpfCustomer.text)

        mDialog.show()

        val btnClose = mDialog.findViewById<Button>(R.id.btnCloseDialogCustomer)
        btnClose.setOnClickListener(View.OnClickListener {
            mDialog.dismiss()
        })

        val btnAlterCustomer = mDialog.findViewById<Button>(R.id.btnSaveCustomer)

        btnAlterCustomer.setOnClickListener {
            mCustomerBusiness = CustomerBusiness(itemView.context)
            if (mCustomerBusiness.alterCustomer(Customer(
                    idCustomer.text.toString().toInt(),
                    name = edtNameCustomer.text.toString(),
                    email = edtEmailCustomer.text.toString(),
                    cpf = edtCPFCustomer.text.toString(),
                    telephone = edtTelephoneCustomer.text.toString()
                )) > 0) {
                nameCustomer.text = edtNameCustomer.text.toString()
                emailCustomer.text = edtEmailCustomer.text.toString()
                cpfCustomer.text = edtCPFCustomer.text.toString()
                telephoneCustomer.text = edtTelephoneCustomer.text.toString()
                mDialog.dismiss()
            }
        }
    }



}