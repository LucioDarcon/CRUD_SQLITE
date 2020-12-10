package com.example.controlsales.view

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.controlsales.R
import com.example.controlsales.business.AdmBusiness
import com.example.controlsales.entities.Adm
import com.example.controlsales.util.SecurityPreferences
import com.google.gson.JsonObject
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.koushikdutta.async.future.FutureCallback
import com.koushikdutta.ion.Ion
import kotlinx.android.synthetic.main.activity_auth.*
import kotlinx.android.synthetic.main.activity_panel.*


class AuthActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mSharedPreferences: SecurityPreferences
    private lateinit var mAdmBusiness: AdmBusiness
    private var arrayPermissionGranted = arrayListOf<Boolean>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        if (!checkPermission()) {
            mSharedPreferences = SecurityPreferences(this)
            setListeners()

            if (verifyAfterLogin()) redirectPanel()
        }
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    private fun checkPermission(): Boolean {
        Dexter.withContext(this)
            .withPermissions(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.INTERNET,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_NETWORK_STATE
            )
            .withListener(object : MultiplePermissionsListener {

                override fun onPermissionsChecked(p0: MultiplePermissionsReport?) {
                    arrayPermissionGranted.add(true)
                }

                override fun onPermissionRationaleShouldBeShown(
                    p0: MutableList<PermissionRequest>?,
                    p1: PermissionToken?
                ) {
                    arrayPermissionGranted.add(false)
                }

            }).check()

        return arrayPermissionGranted.contains(false)

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnLogin -> {
                try {
                    if (validationFields()) {
                        Ion.with(this)
                            .load("http://10.0.0.106/dashboard/projetos/ask/public/admin")
                            .asJsonArray()
                            .setCallback { e, result ->
                                if (result != null) {
                                    for (r in result) {
                                        val responseEmail    = r.asJsonObject.get("email").toString()
                                        val responsePassword = r.asJsonObject.get("password").toString()
                                    if (edtEmail.text.toString() == responseEmail.replace("\"", "") && responsePassword.replace("\"", "") == edtPassword.text.toString()) {
                                            redirectPanel()
                                        } else {
                                            Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()
                                        }
                                    }
                                }
                            }
                    } else {
                        Toast.makeText(
                            this,
                            resources.getString(R.string.preencha_os_campos),
                            Toast.LENGTH_LONG
                        ).show()
                    }
                } catch (e: Exception) {
                    Toast.makeText(this, e.message.toString(), Toast.LENGTH_LONG).show()
                }
            }
            R.id.imgAddAdmin -> {
                val i = Intent(this, RegisterActivity::class.java)
                startActivity(i)
            }
        }
    }

    private fun setListeners() {
        btnLogin.setOnClickListener(this)
        imgAddAdmin.setOnClickListener(this)
    }

    private fun validationFields(): Boolean {
        return edtEmail.text.toString() != "" && edtPassword.text.toString() != ""
    }

    private fun mapArray(arrayAdmin: ArrayList<Adm>) {
        for (i in arrayAdmin) {
            mSharedPreferences.storeString("idAdm", i.id.toString())
            mSharedPreferences.storeString("name", i.name)
            mSharedPreferences.storeString("email", i.email)
        }
    }

    private fun verifyAfterLogin(): Boolean {
        mSharedPreferences = SecurityPreferences(this)
        return mSharedPreferences.getStoredString("name") != "" &&
                mSharedPreferences.getStoredString("email") != ""
    }

    private fun redirectPanel() {
        val i = Intent(this, PanelActivity::class.java)
        startActivity(i)
        finish()
    }
}
