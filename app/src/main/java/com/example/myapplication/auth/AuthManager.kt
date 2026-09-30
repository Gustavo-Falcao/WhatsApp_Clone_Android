package com.example.myapplication.auth

import android.provider.ContactsContract

object AuthManager {
    data class User(
        val nome: String,
        val telefone: String
    )

    private val usuarios = mutableListOf<User>()

    fun login(nome: String, telefone: String): Boolean {
        return usuarios.any { it.telefone == telefone }
    }

    fun cadastrar(nome: String, telefone: String): Boolean {
        if(usuarios.any { it.telefone == telefone }) return false

        usuarios.add(User(nome, telefone))
        return true
    }
}