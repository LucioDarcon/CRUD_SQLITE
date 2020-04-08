package com.example.controlsales.business

import android.content.Context
import com.example.controlsales.dto.LoginDTO
import com.example.controlsales.entities.Adm
import com.example.controlsales.repository.AdmRepository

class AdmBusiness(context: Context) {

    private val admRepository = AdmRepository.getInstance(context)

    fun insertAdm(adm: Adm){
        try {
            selectAdm(adm.email)
            var result = admRepository.insertAdm(adm)
            when(result) {
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

    fun authenticationAdmin(mLoginDTO: LoginDTO): ArrayList<Adm>{
        val arrayListAdmin : ArrayList<Adm>
        try {
            arrayListAdmin =  admRepository.authAdmin(mLoginDTO)
            if(arrayListAdmin.isEmpty()) throw Exception("Usuário não existe")
        }catch (e: Exception){
            throw e
        }
        return arrayListAdmin
    }

}