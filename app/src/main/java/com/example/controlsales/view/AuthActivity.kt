package com.example.controlsales.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.controlsales.R
import com.example.controlsales.util.SecurityPreferences
import kotlinx.android.synthetic.main.activity_auth.*

class AuthActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mSharedPreferences : SecurityPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        supportActionBar?.title = resources.getString(R.string.title_action_bar)
        setListeners()
    }

    override fun onClick(v: View) {
        when(v.id) {
            R.id.btnLogin -> {

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


}
