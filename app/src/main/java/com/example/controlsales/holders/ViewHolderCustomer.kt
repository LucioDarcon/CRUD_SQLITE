package com.example.controlsales.holders

import android.app.Dialog
import android.content.Intent
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
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
import com.example.controlsales.view.PanelActivity
import kotlinx.android.synthetic.main.recycler_customer.view.*
import kotlinx.android.synthetic.main.search_customer_fragment.view.*

class ViewHolderCustomer constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val nameCustomer: TextView = itemView.itemCustomerName
    private val emailCustomer: TextView = itemView.itemCustomerEmail
    private val telephoneCustomer: TextView = itemView.itemCustomerTelephone
    private val cpfCustomer: TextView = itemView.itemCustomerCpf
    private lateinit var mCustomerBusiness: CustomerBusiness


    fun bind(customer: Customer) {
        nameCustomer.text = customer.name
        emailCustomer.text = customer.email
        telephoneCustomer.text = customer.telephone
        cpfCustomer.text = customer.cpf

        val imgEdit = itemView.findViewById<ImageView>(R.id.imgEditCustomer)
        imgEdit.setOnClickListener(View.OnClickListener {
            showDialogAlterCustomer(customer)
        })

        itemView.setOnClickListener(View.OnClickListener {
            Toast.makeText(itemView.context, nameCustomer.text, Toast.LENGTH_LONG).show()
        })
    }

    private fun showDialogAlterCustomer(customer: Customer) {
        val mDialog = Dialog(itemView.context)
        mDialog.setContentView(R.layout.dialog_alter_customer)

        val edtNameCustomer = mDialog.findViewById<EditText>(R.id.edtNameCustomer)
        edtNameCustomer.setText(customer.name)

        val edtEmailCustomer = mDialog.findViewById<EditText>(R.id.edtEmailCustomer)
        edtEmailCustomer.setText(customer.email)

        val edtTelephoneCustomer = mDialog.findViewById<EditText>(R.id.edtTelephoneCustomer)
        edtTelephoneCustomer.setText(customer.telephone)

        val edtCPFCustomer = mDialog.findViewById<EditText>(R.id.edtCpfCustomer)
        edtCPFCustomer.setText(customer.cpf)

        mDialog.show()

        val btnClose = mDialog.findViewById<Button>(R.id.btnCloseDialogCustomer)
        btnClose.setOnClickListener(View.OnClickListener {
            mDialog.dismiss()
        })

        val btnAlterCustomer = mDialog.findViewById<Button>(R.id.btnSaveCustomer)

        btnAlterCustomer.setOnClickListener {
            mCustomerBusiness = CustomerBusiness(itemView.context)
            if (mCustomerBusiness.alterCustomer(getEdtTextFromDialog(mDialog)) > 0) {
                val i = Intent(itemView.context, PanelActivity::class.java)
                ContextCompat.startActivity(itemView.context, i, null)
                mDialog.dismiss()
            }
        }
    }

    private fun getEdtTextFromDialog(mDialog: Dialog): Customer{
        val name = mDialog.findViewById<EditText>(R.id.edtNameCustomer)
        val email = mDialog.findViewById<EditText>(R.id.edtEmailCustomer)
        val telephone = mDialog.findViewById<EditText>(R.id.edtTelephoneCustomer)
        val cpf = mDialog.findViewById<EditText>(R.id.edtCpfCustomer)
        return Customer(name.text.toString(),email.text.toString(),telephone.text.toString(),cpf.text.toString())
    }

}