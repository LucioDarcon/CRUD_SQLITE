package com.example.controlsales.converters

import com.example.controlsales.dto.CustomerDto
import com.example.controlsales.entities.Customer

object ConverterCustomer {

    fun converterCustomerToCustomerDto(customer: Customer) : CustomerDto {
        return CustomerDto(customer.name, customer. email, customer.telephone, customer.cpf, customer.image)
    }

}