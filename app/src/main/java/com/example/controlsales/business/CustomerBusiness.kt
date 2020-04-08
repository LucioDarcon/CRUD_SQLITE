package com.example.controlsales.business

import android.content.Context
import com.example.controlsales.entities.Customer
import com.example.controlsales.repository.CustomerRepository

class CustomerBusiness(context: Context) {

    private val customerRepository = CustomerRepository.getInstance(context)

    fun insertCustomer(mCustomer: Customer): Int{
        var result = 0
        try{
            result = customerRepository.insertCustomer(mCustomer)
        }catch (e: Exception){
            throw e
        }
        return result
    }
}