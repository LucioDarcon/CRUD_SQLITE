package com.example.controlsales.business

import android.content.Context
import com.example.controlsales.entities.Adm
import com.example.controlsales.repository.AdmRepository

class AdmBusiness(val context: Context) {

    private val admRepository = AdmRepository.getInstance(context)
    object RESULT{
        var VALUE = 0
    }

    fun insertAdm(adm: Adm){
        try {
            RESULT.VALUE = admRepository.insertAdm(adm)
            when(RESULT.VALUE) {
                0 -> {
                    throw Exception("Erro ao Salvar!")
                }
            }
        }catch (e: Exception){
            throw e
        }
    }

}