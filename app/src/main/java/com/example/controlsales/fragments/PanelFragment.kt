package com.example.controlsales.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.controlsales.R

class PanelFragment : Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(R.layout.panel_fragment, container, false)
        val btn = view.findViewById<Button>(R.id.btnFragmentPanel)


        btn.setOnClickListener(View.OnClickListener {
            Toast.makeText(context, "Ola mundo Fragment!", Toast.LENGTH_LONG).show()
        })

        return view
    }

}
