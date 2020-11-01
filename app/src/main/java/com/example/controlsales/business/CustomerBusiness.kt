package com.example.controlsales.business

import android.content.Context
import com.example.controlsales.entities.Customer
import com.example.controlsales.repository.CustomerRepository
import kotlin.collections.ArrayList

class CustomerBusiness(context: Context) {

    private val customerRepository = CustomerRepository.getInstance(context)

    fun insertCustomer(mCustomer: Customer): Int{
        var result = 0
        result = try{
            if (mCustomer.id == 0) {
                customerRepository.insertCustomer(mCustomer)
            } else {
                customerRepository.alterCustomer(mCustomer)
            }
        }catch (e: Exception){
            throw e
        }
        return result
    }

    fun getAllCustomer(): ArrayList<Customer>{
        try {
            return customerRepository.getAllCustomer()
        }catch (e: Exception){
            throw e
        }
    }

    fun alterCustomer(mCustomer: Customer): Int{
        try{
            return customerRepository.alterCustomer(mCustomer)
        }catch (e: Exception){
            throw e
        }
    }

    fun getCustomer(id: String): Customer{
        try{
            return customerRepository.getCustomer(id)
        }catch (e: Exception){
            throw e
        }
    }

    fun deleteCustomer(id: String): Int{
        try{
            return customerRepository.deleteCustomer(id)
        }catch (e: Exception){
            throw e
        }
    }

}