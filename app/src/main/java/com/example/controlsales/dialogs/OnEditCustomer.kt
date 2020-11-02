package com.example.controlsales.dialogs

import android.app.Activity
import android.net.Uri
import androidx.fragment.app.FragmentActivity

interface OnEditCustomer {

    interface View {
        fun updateData()

        fun getImage()

        fun getInstanceDialog(mDialog: RegisterCustomerDialog)

    }

    interface Dialog {
        fun showImage(uri: Uri, path: String)

    }


}