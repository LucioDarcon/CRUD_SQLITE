package com.example.controlsales.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.controlsales.R
import com.example.controlsales.business.AdmBusiness
import com.example.controlsales.entities.Adm
import com.example.controlsales.repository.AdmRepository
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var admBusiness: AdmBusiness
    private lateinit var adm: Adm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        setListeners()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnRegisterAdm -> {
                try {
                    if (validationFields()) {
                        admBusiness = AdmBusiness(this)
                        adm = Adm(
                            name = edtRegisterNameAdm.text.toString(),
                            email = edtRegisterEmailAdm.text.toString(),
                            password = edtRegisterPasswordAdm.text.toString()
                        )
                        admBusiness.insertAdm(adm)
                        Toast.makeText(this, resources.getString(R.string.salvo_com_sucesso), Toast.LENGTH_LONG).show()
                        val i = Intent(this, AuthActivity::class.java)
                        startActivity(i)
                    } else {
                        Toast.makeText(this, resources.getString(R.string.preencha_os_campos), Toast.LENGTH_LONG).show()
                    }
                } catch (e: Exception) {
                    Toast.makeText(this, e.message.toString(), Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun setListeners() {
        btnRegisterAdm.setOnClickListener(this)
    }

    private fun validationFields()
            : Boolean {
        return edtRegisterNameAdm.text.toString() != "" && edtRegisterEmailAdm.text.toString() != "" && edtRegisterPasswordAdm.text.toString() != ""
    }

}
