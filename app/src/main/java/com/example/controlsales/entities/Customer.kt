package com.example.controlsales.entities

data class Customer(
    val id: Int = 0,
    val name: String = "",
    val email: String = "",
    val telephone: String = "",
    val cpf: String = "",
    val idAdm: Int = 0
) {
}