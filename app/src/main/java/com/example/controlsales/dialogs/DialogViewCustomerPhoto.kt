package com.example.controlsales.dialogs

import android.app.Dialog
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.example.controlsales.R
import com.example.controlsales.databinding.DialogSeeCustomerPhotoBinding
import com.example.controlsales.entities.Customer
import java.io.File

class DialogViewCustomerPhoto(context: Context, customer: Customer) : Dialog(context) {

    private val mCustomer = customer
    private val mContext = context
    private lateinit var mBinding : DialogSeeCustomerPhotoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.dialog_see_customer_photo,
            null,
            false
        )
        setContentView(mBinding.root)
        setBitmapImage()
        configureWindow()
    }

    private fun configureWindow() {
        this.window?.setBackgroundDrawableResource(R.color.transparent)
    }

    private fun setBitmapImage() {
        if (mCustomer.image != "") {
            mBinding.dialogViewCustomerPhotoImageViewMain.setImageURI(
                Uri.fromFile(
                    File(mCustomer.image)
                )
            )
        } else {
            mBinding.dialogViewCustomerPhotoImageViewMain.setImageDrawable(
                ContextCompat.getDrawable(
                    mContext, R.drawable.ic_camera)
            )
        }
    }



}