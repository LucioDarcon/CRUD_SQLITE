package com.example.controlsales.dto

data class LoginDTO(val email: String = "", val password: String = "") {

    override fun toString(): String {
        return "LoginDTO(email='$email', password='$password')"
    }
}