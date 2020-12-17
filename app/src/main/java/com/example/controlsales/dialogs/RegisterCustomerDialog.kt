package com.example.controlsales.dialogs

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.FrameLayout
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.databinding.DataBindingUtil
import com.example.controlsales.R
import com.example.controlsales.business.CustomerBusiness
import com.example.controlsales.databinding.DialogCustomerBinding
import com.example.controlsales.entities.Customer
import com.example.controlsales.notification.Notification
import com.example.controlsales.util.CreateMaskToTextView
import kotlinx.android.synthetic.main.dialog_customer.*
import java.io.File


class RegisterCustomerDialog(
    context: Context,
    customer: Customer,
    onEditCustomer: OnEditCustomer.View
) : Dialog(context),
    View.OnClickListener, OnEditCustomer.Dialog {

    private lateinit var mCustomerBusiness: CustomerBusiness
    private var mCustomer: Customer = customer
    private lateinit var mBinding: DialogCustomerBinding
    private var mView = onEditCustomer
    private var mPathImageCustomer = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.dialog_customer,
            null,
            false
        )
        mCustomerBusiness = CustomerBusiness(context)

        mView.getInstanceDialog(this)
        setContentView(mBinding.root)
        setListeners()
        configureDialog()
        configureWindow()
        setEntityCustomToTextFieldDialog(mCustomer)
    }


    private fun setEntityCustomToTextFieldDialog(customer: Customer) {
        if (customer.id != 0) {
            mBinding.customer = customer
            if (customer.image == "") {
                mBinding.dialogCustomerCameraImageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        context,
                        R.drawable.ic_camera
                    )
                )
            } else {
                mBinding.dialogCustomerCameraImageView.setImageURI(
                    Uri.fromFile(
                        File(customer.image)
                    )
                )
            }
            mBinding.executePendingBindings()
        } else {
            mBinding.dialogCustomerCameraImageView.setImageDrawable(
                ContextCompat.getDrawable(
                    context,
                    R.drawable.ic_camera
                )
            )
        }
    }

    private fun configureWindow() {
        window?.setBackgroundDrawableResource(android.R.color.transparent)
        window?.setLayout(
            FrameLayout.LayoutParams.MATCH_PARENT,
            FrameLayout.LayoutParams.MATCH_PARENT
        )
    }

    private fun configureDialog() {
        mBinding.layoutCpfCustomer.visibility = View.GONE
        mBinding.edtTelephoneCustomer.addTextChangedListener(
            CreateMaskToTextView.setMaskToTextViewTelephone(
                mBinding.edtTelephoneCustomer
            )
        )
        mBinding.edtCpfCustomer.addTextChangedListener(
            CreateMaskToTextView.setMaskToTextViewCpf(
                mBinding.edtCpfCustomer
            )
        )
        btnSaveCustomer.animation = AnimationUtils.loadAnimation(context, R.anim.from_bottom)
    }

    private fun validationFields(): Boolean {
        return edtNameCustomer.text.toString() != ""
                && edtTelephoneCustomer.text.toString() != ""
    }

    private fun setListeners() {
        btnSaveCustomer.setOnClickListener(this)
        btnCloseDialogCustomer.setOnClickListener(this)
        mBinding.dialogCustomerCameraImageView.setOnClickListener(this)
    }

    private fun saveCustomer() {
        if (validationFields()) {

            var mCustomer = Customer()
            if (mBinding.customer != null) {
                mCustomer = Customer(
                    mBinding.customer!!.id,
                    name = edtNameCustomer.text.toString(),
                    telephone = edtTelephoneCustomer.text.toString(),
                    email = edtEmailCustomer.text.toString(),
                    cpf = edtCpfCustomer.text.toString(),
                    idAdm = mBinding.customer!!.idAdm,
                    image = mPathImageCustomer
                )
            } else {
                mCustomer = Customer(
                    name = edtNameCustomer.text.toString(),
                    telephone = edtTelephoneCustomer.text.toString(),
                    email = edtEmailCustomer.text.toString(),
                    image = mPathImageCustomer
                )
            }

            val result = mCustomerBusiness.insertCustomer(mCustomer)

            if (result > 0) Toast.makeText(context, R.string.salvo_com_sucesso, Toast.LENGTH_LONG).show()
            else Toast.makeText(context, R.string.erro_ao_salvar, Toast.LENGTH_LONG).show()
            mView.updateData()
            dismiss()

            Handler().postDelayed(
                {
                Notification.initNotification(context, "Um novo cliente foi adicionado")
                    Notification.sendNotification(context)
                }, 5000
            )

        }
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btnCloseDialogCustomer -> {
                dismiss()
            }
            R.id.btnSaveCustomer -> {
                saveCustomer()
            }
            R.id.dialog_customer_camera_image_view -> {
                mView.getImage()
            }
        }
    }

    override fun showImage(uri: Uri, path: String) {
        mPathImageCustomer = path
        mBinding.dialogCustomerCameraImageView.setImageURI(uri)
    }


}