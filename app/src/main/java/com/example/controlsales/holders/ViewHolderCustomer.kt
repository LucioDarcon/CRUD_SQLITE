package com.example.controlsales.holders

import android.app.Dialog
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.controlsales.R
import com.example.controlsales.business.CustomerBusiness
import com.example.controlsales.entities.Customer
import com.example.controlsales.recyclerviews.RecyclerViewCustomer
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.recycler_customer.view.*

class ViewHolderCustomer constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var nameCustomer: TextView = itemView.itemCustomerName
    private var emailCustomer: TextView = itemView.itemCustomerEmail
    private var telephoneCustomer: TextView = itemView.itemCustomerTelephone
    private var cpfCustomer: TextView = itemView.itemCustomerCpf
    private var idCustomer: TextView = itemView.itemCustomerId
    private lateinit var mCustomerBusiness: CustomerBusiness
    private lateinit var mRecyclerViewCustomer: RecyclerViewCustomer


    fun assignRecyclerView(recyclerViewCustomer: RecyclerViewCustomer){
        this.mRecyclerViewCustomer = recyclerViewCustomer
    }

    fun bind(customer: Customer) {
        mCustomerBusiness = CustomerBusiness(itemView.context)
        nameCustomer.text = customer.name
        emailCustomer.text = customer.email
        telephoneCustomer.text = customer.telephone
        cpfCustomer.text = customer.cpf
        idCustomer.text = customer.id.toString()

        val imgEdit = itemView.findViewById<ImageView>(R.id.imgEditCustomer)
        imgEdit.setOnClickListener(View.OnClickListener {
            showDialogAlterCustomer()
        })

        val imgDelete = itemView.findViewById<ImageView>(R.id.imgDeleteCustomer)
        imgDelete.setOnClickListener(View.OnClickListener {
            val snack = Snackbar.make(it, "Deseja excluir " + nameCustomer.text.toString() + "?", Snackbar.LENGTH_LONG)
            snack.setAction("Sim", View.OnClickListener {
                mCustomerBusiness.deleteCustomer(idCustomer.text.toString())
                actualizeRecyclerView()
            })
            val snackView = snack.view
            snack.show()
        })

    }


    private fun showDialogAlterCustomer() {
        val mDialog = Dialog(itemView.context)
        mDialog.setContentView(R.layout.dialog_customer)

        val edtNameCustomer = mDialog.findViewById<EditText>(R.id.edtNameCustomer)
        edtNameCustomer.setText(nameCustomer.text)

        val edtEmailCustomer = mDialog.findViewById<EditText>(R.id.edtEmailCustomer)
        edtEmailCustomer.setText(emailCustomer.text)

        val edtTelephoneCustomer = mDialog.findViewById<EditText>(R.id.edtTelephoneCustomer)
        edtTelephoneCustomer.setText(telephoneCustomer.text)

        val edtCPFCustomer = mDialog.findViewById<EditText>(R.id.edtCpfCustomer)
        edtCPFCustomer.setText(cpfCustomer.text)

        mDialog.window?.setBackgroundDrawableResource(android.R.color.transparent);
        mDialog.show()

        val btnClose = mDialog.findViewById<Button>(R.id.btnCloseDialogCustomer)
        btnClose.setOnClickListener(View.OnClickListener {
            mDialog.dismiss()
        })

        val btnAlterCustomer = mDialog.findViewById<Button>(R.id.btnSaveCustomer)

        btnAlterCustomer.setOnClickListener {
            if (mCustomerBusiness.alterCustomer(
                    Customer(
                        idCustomer.text.toString().toInt(),
                        name = edtNameCustomer.text.toString(),
                        email = edtEmailCustomer.text.toString(),
                        cpf = edtCPFCustomer.text.toString(),
                        telephone = edtTelephoneCustomer.text.toString()
                    )
                ) > 0
            ) {
                actualizeRecyclerView()
                mDialog.dismiss()
            }
        }
    }

    fun showDialogRegisterCustomer(view: View, recyclerViewCustomer: RecyclerViewCustomer){
        mCustomerBusiness = CustomerBusiness(itemView.context)
        val fromBottom = AnimationUtils.loadAnimation(view.context, R.anim.from_bottom)
        val mDialog = Dialog(view.context)
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
                    Toast.makeText(view.context, R.string.salvo_com_sucesso, Toast.LENGTH_LONG).show()
                } else{
                    Toast.makeText(view.context, R.string.erro_ao_salvar, Toast.LENGTH_LONG).show()
                }
                recyclerViewCustomer.submitList(mCustomerBusiness.getAllCustomer())
                recyclerViewCustomer.notifyDataSetChanged()
            } catch (e: Exception) {
                Toast.makeText(view.context, R.string.erro_inesperado, Toast.LENGTH_LONG).show()
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

    fun actualizeRecyclerView(){
        mRecyclerViewCustomer.submitList(mCustomerBusiness.getAllCustomer())
        mRecyclerViewCustomer.notifyDataSetChanged()
    }


}