package com.example.controlsales.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.controlsales.R
import com.example.controlsales.business.AdmBusiness
import com.example.controlsales.dto.LoginDTO
import com.example.controlsales.entities.Adm
import com.example.controlsales.util.SecurityPreferences
import kotlinx.android.synthetic.main.activity_auth.*
import kotlinx.android.synthetic.main.activity_panel.*


class AuthActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mSharedPreferences : SecurityPreferences
    private lateinit var mAdmBusiness: AdmBusiness

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        if(verifyAfterLogin()) redirectPanel()
        setListeners()
    }

    override fun onClick(v: View) {
        when(v.id) {
            R.id.btnLogin -> {
                try {
                    if (validationFields()) {
                        mAdmBusiness = AdmBusiness(this)
                        var arrayAdmin = mAdmBusiness.authenticationAdmin(LoginDTO(
                            edtEmail.text.toString(), edtPassword.text.toString()
                        ))
                        mapArray(arrayAdmin)
                        redirectPanel()
                    }else{
                        Toast.makeText(this, resources.getString(R.string.preencha_os_campos), Toast.LENGTH_LONG).show()
                    }
                }catch (e: Exception){
                    Toast.makeText(this, e.message.toString(), Toast.LENGTH_LONG).show()
                }
            }
            R.id.imgAddAdmin -> {
                val i = Intent(this, RegisterActivity::class.java)
                startActivity(i)
            }
        }
    }

    private fun setListeners(){
        btnLogin.setOnClickListener(this)
        imgAddAdmin.setOnClickListener(this)
    }

    private fun validationFields(): Boolean {
        return edtEmail.text.toString() != "" && edtPassword.text.toString() != ""
    }

    private fun mapArray(arrayAdmin: ArrayList<Adm>){
        for (i in arrayAdmin){
            mSharedPreferences.storeString("name", i.name)
            mSharedPreferences.storeString("email", i.email)
        }
    }

    private fun verifyAfterLogin(): Boolean{
        mSharedPreferences = SecurityPreferences(this)
        return mSharedPreferences.getStoredString("name") != "" &&
        mSharedPreferences.getStoredString("email") != ""
    }

    private fun redirectPanel(){
        val i = Intent(this, PanelActivity::class.java)
        startActivity(i)
    }
}
