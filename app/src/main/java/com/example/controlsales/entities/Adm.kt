package com.example.controlsales.entities

data class Adm(val id: Int = 0, val name: String = "", val email: String = "", val password: String = ""){

    override fun toString(): String {
        return "RegisterAdmDTO(name='$name', email='$email', password='$password')"
    }
}