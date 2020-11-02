package com.example.controlsales.dialogs

import android.net.Uri

interface OnEditCustomer {

    interface View {
        fun updateData()

        fun getImage()

        fun getInstanceDialog(mDialog: RegisterCustomerDialog)


    }

    interface Dialog {
        fun showImage(uri: Uri)
    }


}