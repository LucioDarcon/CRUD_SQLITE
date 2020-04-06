package com.example.controlsales.view

import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.example.controlsales.R
import com.example.controlsales.util.SecurityPreferences
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_panel.*
import kotlinx.android.synthetic.main.toolbar.*


class PanelActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var mSharedPreferences: SecurityPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_panel)
        initNavigationDrawerToolbar()
        onCompleteFields()
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(p: MenuItem): Boolean {
        when (p.itemId) {
            R.id.addCustomer -> {
                Toast.makeText(this, "Add Customer", Toast.LENGTH_LONG).show()
            }
            R.id.consultCustomer -> {
                Toast.makeText(this, "Consult Customer", Toast.LENGTH_LONG).show()
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun initNavigationDrawerToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.title = this.resources.getString(R.string.title_action_bar)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.open,
            R.string.close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.isDrawerIndicatorEnabled = true
        toggle.syncState()
        navigationView.setNavigationItemSelectedListener(this)
    }

    private fun onCompleteFields() {
        mSharedPreferences =
            SecurityPreferences(this)
        val headerView = navigationView.getHeaderView(0)
        val headerTxtNameUser = headerView.findViewById<TextView>(R.id.txtNameUser)
        val headerTxtEmailUser = headerView.findViewById<TextView>(R.id.txtEmailUser)
        headerTxtNameUser.text = mSharedPreferences.getStoredString("name")
        headerTxtEmailUser.text = mSharedPreferences.getStoredString("email")
    }
}
