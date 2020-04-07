package com.example.controlsales.business

import android.content.Context
import com.example.controlsales.entities.Adm
import com.example.controlsales.repository.AdmRepository

class AdmBusiness(context: Context) {

    private val admRepository = AdmRepository.getInstance(context)
    object RESULT{
        var VALUE = 0
    }

    fun insertAdm(adm: Adm){
        try {
            selectAdm(adm.email)
            RESULT.VALUE = admRepository.insertAdm(adm)
            when(RESULT.VALUE) {
                0 -> {
                    throw Exception("Erro ao Inesperado!")
                }
            }
        }catch (e: Exception){
            throw e
        }
    }

    private fun selectAdm(email: String){
        try{
            if(admRepository.selectAdm(email)){
                throw Exception("Email já Cadastrado!")
            }
        }catch (e: Exception){
            throw e
        }
    }

}