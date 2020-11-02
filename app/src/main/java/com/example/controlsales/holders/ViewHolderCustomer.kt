package com.example.controlsales.holders

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import android.widget.FrameLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.example.controlsales.R
import com.example.controlsales.converters.ConverterCustomer.converterCustomerToCustomerDto
import com.example.controlsales.databinding.CardCustomerComponentBinding
import com.example.controlsales.entities.Customer
import com.example.controlsales.recyclerviews.AdapterCustomer
import java.io.File

class ViewHolderCustomer constructor(cardCustomerComponentBinding: CardCustomerComponentBinding, context: Context) : RecyclerView.ViewHolder(cardCustomerComponentBinding.root) {

    private var mCardCustomerComponentBinding = cardCustomerComponentBinding
    private val mContext = context

    fun bind(customer: Customer, mOnCLickCustomer: AdapterCustomer.OnClickCustomer) {

        mCardCustomerComponentBinding.cardCustomerComponentDeleteCustomerImageView.setOnClickListener {
            mOnCLickCustomer.onClickEditCustomer(customer)
        }

        mCardCustomerComponentBinding.cardCustomerComponentGeneralCardView.setOnLongClickListener {
            mOnCLickCustomer.onClickDeleteCustomer(customer, mCardCustomerComponentBinding)
            true
        }

        if (customer.image != "") {
            mCardCustomerComponentBinding.cardCustomerComponentCustomerImageView.setImageURI(
                Uri.fromFile(
                    File(customer.image)
                )
            )
        } else {
            mCardCustomerComponentBinding.cardCustomerComponentCustomerImageView.setImageDrawable(ContextCompat.getDrawable(
                mContext, R.drawable.ic_baseline_camera_alt_24)
            )
        }

        mCardCustomerComponentBinding.customerDto = converterCustomerToCustomerDto(customer)

    }

}