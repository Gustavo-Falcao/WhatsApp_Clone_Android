package com.example.myapplication.ui.viewmodel

import android.app.Application
import android.database.sqlite.SQLiteConstraintException
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.auth.AuthManager
import com.example.myapplication.data.dao.UsuarioDAO
import com.example.myapplication.validation.CadastroValidator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AuthViewModel(application: Application): AndroidViewModel(application) {
    private val dao = UsuarioDAO(application)
    var carregando by mutableStateOf(false)
        private set
    var mensagem by mutableStateOf<String?>(null)
        private set
    var concluido by mutableStateOf(false)
        private set

    private fun executar(op: () -> Boolean) {
        if(carregando || concluido) return
        carregando = true
        mensagem = null

        viewModelScope.launch {
            try {
                concluido = withContext(Dispatchers.IO){op()}
                if(!concluido) mensagem = "Telefone ou senha inválido"
            } catch (e: SQLiteConstraintException) {
                mensagem = "Não foi possivel cadastrar. Confira se o email já esta cadastrado"
            } catch (e: IllegalStateException) {
                mensagem = e.message
            } catch (e: Exception) {
                mensagem = "Ocorreu um erro inseperado"
            } finally {
                carregando = false
            }
        }
    }

    fun cadastrar(nome: String, telefone: String, senha: String) = executar {
        val nomeLimpo = nome.trim()
        val telefoneLimpo = telefone.trim()
        val senhaLimpa = senha.trim()

        CadastroValidator.validar(nomeLimpo, telefoneLimpo, senhaLimpa)
        val salt = AuthManager.criarSalt()
        val senhaHash = AuthManager.gerarHash(senha, salt)
        try {
            dao.cadastrar(nomeLimpo, telefoneLimpo, senhaHash, salt)
            true
        } finally {
            senhaHash.fill(0)
        }
    }

    fun login(telefone: String, senha: String) = executar {
        require(telefone.length <= 11 && senha.length in 6 .. 128) {
            "Telefone ou senha inválidos"
        }

        val dados = dao.buscarSenha(telefone.trim())
        val senhaHash = dados?.hash ?: ByteArray(32)
        val salt = dados?.salt ?: ByteArray(16)

        try {
            val autenticado = AuthManager.validarSenha(senha, salt, senhaHash)
            dados != null && autenticado
        } finally {
            senhaHash.fill(0)
        }
    }

    fun consumirResultado(){ concluido = false }

}